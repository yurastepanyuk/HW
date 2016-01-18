package tanks;

import tanks.battlefieldobjects.BattleFieldObjects;
import tanks.battlefieldobjects.Brick;
import tanks.battlefieldobjects.Eagle;
import tanks.battlefieldobjects.Empty;
import tanks.battlefieldobjects.Rock;
import tanks.commoninterface.BattleFieldObject;
import tanks.commoninterface.Tank;
import tanks.enums.*;
import tanks.enums.Action;
import tanks.tanksobject.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class ActionField extends JPanel {
	
	private BattleField battleField;

	private long 		timeCreateNewAgressor;
	private long 		timeCreateDefender;
	private String		strSelectTank;

	private List<Tank>  agressors;
	private List<Tank>	defenders;

	private Tank		player;
	private AtomicBoolean	 keyMovePressed;
	private AtomicLong		 timeAddMove;
	private final CopyOnWriteArrayList<Bullet> shotPlayer = new CopyOnWriteArrayList<>();

	private final CopyOnWriteArraySet<Bullet> shotsBullet = new CopyOnWriteArraySet<>();
	private final Queue<Bullet>				  bulletQueue = new LinkedBlockingQueue<>();

	private JFrame 		frame;
    private JPanel	 	panelGameOptions;
	private JPanel	 	panelGameResult;

	private GameMode 	gameMode;

	private JRadioButton rbutton;
	private ButtonGroup  buttonGroupSelectTank;

	private ExecutorService serviceThread;

	public final String LOG_NAME = System.getProperty("user.dir") + "\\" + "LogTanks.lg";
	public boolean flRepeaatGame = false;
	public Map<String, List<Map<String, Object>>>  mapLog;

 	public ActionField() throws Exception {

		serviceThread = Executors.newCachedThreadPool();
		//newFixedThreadPool(5);

		frame = new JFrame("BATTLE FIELD, DAY 12");
		frame.setLocation(500, 100);
		frame.setMinimumSize(new Dimension(BattleField.BF_WIDTH + 10, BattleField.BF_HEIGHT + 22 + 14));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBackground(Color.BLACK);

		inicialisationAF();

		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new MyDispatcher());

		gameMode = GameMode.OPTIONS;
		//gameMode = GameMode.GAME;

		panelGameOptions = creatingOptionsPanel();
		panelGameResult	 = creatingTotalPanel();

		switchingPanels(GameMode.OPTIONS);

		serviceThread.submit(startIndependentScreenUpdate());

		FileOutputStream fileOutputStream = new FileOutputStream(LOG_NAME);
		fileOutputStream.close();

	}

	private Runnable startIndependentScreenUpdate() {
		return new Runnable() {
			@Override
			public void run() {
				while (true){
					repaint();

					sleep(1000/60);

				}
			}
		};
	}

	private void switchingPanels(GameMode newGameMode){
		gameMode = newGameMode;

		//frame.removeAll();

		for (Component component : frame.getContentPane().getComponents()){
			frame.getContentPane().remove(component);

		}

		if (gameMode == GameMode.GAME){
			frame.getContentPane().add(this);
		} else if (gameMode == GameMode.OPTIONS){
			frame.getContentPane().add(panelGameOptions);
		} else if (gameMode == GameMode.RESULT){
			frame.getContentPane().add(panelGameResult);
		}

		repaint();

		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();

		frame.pack();
		frame.setVisible(true);
	}

	private void inicialisationAF(){
		battleField = new BattleField(this);

		timeCreateNewAgressor = System.currentTimeMillis() + 1000*3;//+3 sec

		keyMovePressed = new AtomicBoolean(false );
		timeAddMove	   = new AtomicLong(0);

		defenders 	  = new ArrayList<>();
		agressors 	  = new ArrayList<>();

		//frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);

		try {
			for (Tank curTank : defenders){
				if (curTank.isDestroyed()) continue;
				printTanksInfo(curTank);
			}

			for (Tank curTank : agressors){
				if (curTank.isDestroyed()) continue;
				printTanksInfo(curTank);
			}
		} catch (NoSuchFieldException|IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	private JPanel creatingOptionsPanel() throws Exception{

		URL urlImageNameGame= Launcher.class.getResource("images/battletank.jpg");
		Image imageNameGame = ImageIO.read(urlImageNameGame);
		URL urlimageT34		= Launcher.class.getResource("images/t34up.jpg");
		Image imageT34 = ImageIO.read(urlimageT34);
		URL urlimageBT7		= Launcher.class.getResource("images/bt7Up.jpg");
		Image imageBT7 = ImageIO.read(urlimageBT7);
		URL urlimageTiger	 	= Launcher.class.getResource("images/tigerUp.jpg");
		Image imageTiger = ImageIO.read(urlimageTiger);

		JPanel panel = new JPanel();

		panel.setBackground(Color.BLACK);

		panel.setLayout(new GridBagLayout());

		Icon iconGame 	 = new ImageIcon(imageNameGame);

		JLabel labelGame = new JLabel("", iconGame, JLabel.CENTER);
		panel.add(labelGame, new GridBagConstraints(0,0,3,1,0,0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));


		JLabel labelSpace = new JLabel();
		panel.add(labelSpace, new GridBagConstraints(1,1,3,1,0,0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));

		JLabel labelSelected = new JLabel("Select the tank of agressor");
		Font fontLabel		 = new Font("Courier New", Font.PLAIN, 14);
		labelSelected.setFont(fontLabel);
		labelSelected.setForeground(Color.white);
		panel.add(labelSelected, new GridBagConstraints(1,2,3,1,0,0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));

		JPanel panelTanks = new JPanel();
		panelTanks.setBackground(Color.BLACK);
		panelTanks.setForeground(Color.BLACK);
		panelTanks.setBorder(null);
		panelTanks.setLayout(new GridLayout(0, 3));
		panelTanks.setBorder(BorderFactory.createLineBorder(Color.lightGray));

		Icon iconT34	= new ImageIcon(imageT34);
		Icon iconBT7	= new ImageIcon(imageBT7);
		Icon iconTiger	= new ImageIcon(imageTiger);

		buttonGroupSelectTank = new ButtonGroup();
		ActionListener rbListener = new RBListener();

		rbutton = new JRadioButton("T34",iconT34);
		rbutton.addActionListener(rbListener);
		rbutton.setActionCommand("T34");
		rbutton.setSelected(true);
		//rbutton.setBackground(Color.BLACK);
		rbutton.setContentAreaFilled(false);
		buttonGroupSelectTank.add(rbutton);
		panelTanks.add(rbutton);

		rbutton 	= new JRadioButton("BT7",iconBT7);
		rbutton.addActionListener(rbListener);
		rbutton.setActionCommand("BT7");
		rbutton.setContentAreaFilled(false);
		buttonGroupSelectTank.add(rbutton);
		panelTanks.add(rbutton);

		rbutton 	= new JRadioButton("Tiger",iconTiger);
		rbutton.addActionListener(rbListener);
		rbutton.setActionCommand("Tiger");
		rbutton.setContentAreaFilled(false);
		buttonGroupSelectTank.add(rbutton);
		panelTanks.add(rbutton);

		panel.add(panelTanks, new GridBagConstraints(1,3,3,1,0,0,GridBagConstraints.LINE_START,0, new Insets(0,0,0,0),0,0));

		JButton buttonStart = new JButton("Start");
		buttonStart.setActionCommand("start");
		ActionListener buttonListener = new ButtonListener(this);
		buttonStart.addActionListener(buttonListener);

		panel.add(buttonStart, new GridBagConstraints(2,4,1,1,0,0,GridBagConstraints.CENTER,0, new Insets(0,0,0,0),0,0));

		return panel;

	}

	private JPanel creatingTotalPanel() throws Exception{

		URL urlImageNameGame= Launcher.class.getResource("images/total.jpg");
		Image imageTotal = ImageIO.read(urlImageNameGame);
		URL urlimageT34		= Launcher.class.getResource("images/t34up.jpg");
		Image imageT34 = ImageIO.read(urlimageT34);
		URL urlimageBT7		= Launcher.class.getResource("images/bt7Up.jpg");
		Image imageBT7 = ImageIO.read(urlimageBT7);
		URL urlimageTiger	 	= Launcher.class.getResource("images/tigerUp.jpg");
		Image imageTiger = ImageIO.read(urlimageTiger);

		JPanel panel = new JPanel();

		panel.setBackground(Color.BLACK);

		panel.setLayout(new GridBagLayout());

		Icon iconTotal 	 = new ImageIcon(imageTotal);
		JLabel labelTotal = new JLabel("", iconTotal, JLabel.CENTER);
		panel.add(labelTotal, new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));


		JLabel labelSpace = new JLabel();
		panel.add(labelSpace, new GridBagConstraints(0,1,1,1,0,0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));

		JPanel panelTanks = new JPanel();
		panelTanks.setBackground(Color.BLACK);
		panelTanks.setForeground(Color.BLACK);
		panelTanks.setBorder(null);
		panelTanks.setLayout(new GridLayout(3, 0));
		panelTanks.setBorder(BorderFactory.createLineBorder(Color.lightGray));

		Icon iconT34	= new ImageIcon(imageT34);
		Icon iconBT7	= new ImageIcon(imageBT7);
		Icon iconTiger	= new ImageIcon(imageTiger);

		JLabel label = new JLabel("",iconT34, JLabel.CENTER);
		label.setBorder(null);
		panelTanks.add(label);

		label 	= new JLabel("",iconBT7, JLabel.CENTER);
		label.setBorder(null);
		panelTanks.add(label);

		label 	= new JLabel("",iconTiger, JLabel.CENTER);
		label.setBorder(null);
		panelTanks.add(label);

		panel.add(panelTanks, new GridBagConstraints(0,2,1,1,0,0,GridBagConstraints.CENTER,0, new Insets(0,0,0,0),0,0));

		labelSpace = new JLabel();
		panel.add(labelSpace, new GridBagConstraints(1,1,1,1,0,0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));

		JButton buttonStart = new JButton("Get options");
		buttonStart.setActionCommand("options");
		ActionListener buttonListener = new ButtonListener(this);
		buttonStart.addActionListener(buttonListener);

		panel.add(buttonStart, new GridBagConstraints(0,4,1,1,0,0,GridBagConstraints.CENTER,0, new Insets(20,0,20,0),0,0));

		JButton buttonRepeatGame = new JButton("Repeat Game");
		buttonRepeatGame.setActionCommand("repeatGame");
		buttonRepeatGame.addActionListener(buttonListener);
		panel.add(buttonRepeatGame, new GridBagConstraints(0,5,1,1,0,0, GridBagConstraints.CENTER,0, new Insets(0,0,0,0),0,0));

		return panel;

	}

	private class ButtonListener implements ActionListener {

		ActionField actionField;

		public ButtonListener(ActionField actionField) {
			this.actionField = actionField;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("start")) {
				inicialisationAF();
				switchingPanels(GameMode.GAME);

				flRepeaatGame = false;

				Thread thr = new Thread(){
					@Override
					public void run(){
						runTheGame();
					}
				};
				thr.start();
			} else if (e.getActionCommand().equals("options")){
				switchingPanels(GameMode.OPTIONS);
			} else if (e.getActionCommand().equals("repeatGame")){

				flRepeaatGame = true;

				mapLog = logBattleRestore();

				if (mapLog == null) {
					return;
				}

				switchingPanels(GameMode.OPTIONS);

				inicialisationAF();
				switchingPanels(GameMode.GAME);

				Thread thr = new Thread(){
					@Override
					public void run(){
						runTheGame();
					}
				};
				thr.start();
			}

		}

	}

	private Map<String, List<Map<String, Object>>> logBattleRestore(){

		Map<String, List<Map<String, Object>> > logTanks   = new HashMap<>();
		List<Map<String, Object>> actionListDefender= new ArrayList<>();
		List<Map<String, Object>> actionListAgressor= new ArrayList<>();

		try(FileReader fileReaderLogBattle = new FileReader(LOG_NAME);
			BufferedReader bufReaderLogBattle = new BufferedReader(fileReaderLogBattle)) {

			String curLogTank = "";

			while ((curLogTank = bufReaderLogBattle.readLine()) != null){
				String [] curAction = curLogTank.split(";");
				//Action;AGRESSOR;TURN;DOWN;0;8;
				String ltypeLogRecord = curAction[0].trim();
				String ltypeTank	  = curAction[1].trim();
				String ltypeAction	  = curAction[2].trim();
				String lDirection	  = curAction[3].trim();
				String lY			  = curAction[4].trim();
				String lX			  = curAction[5].trim();
				String lnameClass	  = curAction[6].trim();

				Action actionTank 		= Action.valueOf(ltypeAction);
				Direction directionTank = Direction.valueOf(lDirection);
				Integer quadrantY		= Integer.valueOf(lY);
				Integer quadrantX		= Integer.valueOf(lX);
				Behavior behaviorTank	= ltypeTank.equals("DEFENDER")?Behavior.DEFENDER:Behavior.AGRESSOR;

				Map<String, Object> actionMapTank = new HashMap<>();

				if (ltypeLogRecord.equals("Action")){

					actionMapTank.put("direction", directionTank);
					actionMapTank.put("action"	 , actionTank);
					actionMapTank.put("quadrantY", quadrantY);
					actionMapTank.put("quadrantX", quadrantX);

					if (ltypeTank.equals("DEFENDER")){

						actionListDefender.add(actionMapTank);

					} else if (ltypeTank.equals("AGRESSOR")){

						 actionListAgressor.add(actionMapTank);

					}

				} else if (ltypeLogRecord.equals("GENERATE")){

					//GENERATE;DEFENDER;NONE;UP;8;2;
					Class<?> clsTank 	= null;
					Tank  createTank 	= null;
					Class[] paramConstruktor = new Class[2];
					paramConstruktor[0] = battleField.getClass();
					paramConstruktor[1] = behaviorTank.getClass();

					try {
						clsTank 	= Class.forName("tanks.tanksobject."+ lnameClass);
						Constructor constructorTank = clsTank.getConstructor(paramConstruktor);
						//Class<?> ddd = Class.forName("tanks.tanksobject."+ lnameClass);
						createTank  = (Tank)constructorTank.newInstance(battleField, behaviorTank);
						//Tank createTank	= (Tank)obj;
						((AbstractTank) createTank).setY(quadrantY * 64);
						((AbstractTank) createTank).setX_(quadrantX * 64);
						((AbstractTank) createTank).setHandControl(true);
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException  e) {
						e.printStackTrace();
						return null;
					}

					if (ltypeTank.equals("DEFENDER")){
						((AbstractTank) createTank).setHandControl(false);
						createTank.setTargetForTank();

						actionMapTank.put("DEFENDER", createTank);
						actionListDefender.add(actionMapTank);

					} else if (ltypeTank.equals("AGRESSOR")){
						((AbstractTank)createTank).setColor(Colors.ORANGE);
						((AbstractTank)createTank).bullet = new Bullet();
						createTank.setTarget(battleField.getQuadrantDefender());

						timeCreateNewAgressor = 0;

						createTank.setTargetForTank();

						actionMapTank.put("AGRESSOR", createTank);
						actionListAgressor.add(actionMapTank);
					}

				}
			}

			logTanks.put("ActionDefender", actionListDefender);
			logTanks.put("ActionAgressor", actionListAgressor);

			Tank searchTank = null;
			searchTank = getTankFromLog("DEFENDER", logTanks);
			if (searchTank != null) {
				((AbstractTank)searchTank).setActionTank(actionListDefender);
			}

			searchTank = getTankFromLog("AGRESSOR", logTanks);
			if (searchTank != null) {
				((AbstractTank)searchTank).setActionTank(actionListAgressor);
			}

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return logTanks;
	}

	private Tank getTankFromLog(String typeBehavior, Map log){

		List<Map<String, Object>> listAction;

		if (typeBehavior.equals("DEFENDER")){
			listAction = (List)log.get("ActionDefender");
		} else {
			listAction = (List)log.get("ActionAgressor");
		}

		for (Map<String, Object> curAction : listAction) {

			boolean isTank = curAction.containsKey(typeBehavior);

			if (isTank) {
				return (Tank) curAction.get(typeBehavior);
			}

		}

		return null;
	}

	private class RBListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			strSelectTank = e.getActionCommand();
			//Tank agressor 	  = getNewAgressor();

		}
	}

	private AbstractTank getNewAgressor() {

 		Random r = new Random();

 		int X = 0;

 		int idx = 0;

 		int i = 0;
 		while (i < 3) {
 			idx = r.nextInt(3);
 			i++;
 			//System.out.println("Random idx: " + idx);
		}

 		if (idx == 0) {
			X = 0;
		} else if (idx == 1){
			X = 4;
		} else if (idx == 2){
			X = 8;
		}

		AbstractTank agres = null;

		if (strSelectTank == null){
			agres = new Tiger(getBattleField(), Behavior.AGRESSOR);
		} else if (strSelectTank.equals("T34")){
			agres = new T34(getBattleField(), Behavior.AGRESSOR);
		} else if (strSelectTank.equals("BT7")){
			agres = new BT7(getBattleField(), Behavior.AGRESSOR);
		} else if (strSelectTank.equals("Tiger")){
			agres = new Tiger(getBattleField(), Behavior.AGRESSOR);
		}

		agres.setX_(X*64);
		agres.setY(0);
		agres.setTankDirection(Direction.DOWN);
		agres.setColor(Colors.ORANGE);
		agres.bullet = new Bullet();
		agres.setTarget(battleField.getQuadrantDefender());

		timeCreateNewAgressor = 0;

		return agres;
	}

	public void printTanksInfo(Tank tank) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{

		System.out.println("Color of tank: "  + tank.getColor());
		System.out.println("Speed os tank: " + tank.getSpeed() );
 			if (tank.getClass().getSimpleName().equals("Tiger")) {
 				Class c = tank.getClass();
 				Field field = c.getField("armor");
 				int Value = (int) field.get(tank);
 				System.out.println("Armor of tank: " + Value);
			}
		tank.printTankInfo();

	};

	private class RunTheGameTank implements Callable{

		private Tank tankRun;

		public RunTheGameTank(Tank tankRun) {
			this.tankRun = tankRun;
			if (tankRun != null) {
				if (((AbstractTank)tankRun).getBehavior() == Behavior.AGRESSOR){

					agressors.add(tankRun);

				} else {
					defenders.add(tankRun);
				}
			}
			try {
				logAction("GENERATE", tankRun, Action.NONE, tankRun.getDirection());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void ifSeeingTargetThenShot(){
			if (!((AbstractTank) tankRun).isHandControl() && !flRepeaatGame) {
				int qtyShotOnTarget = tankRun.isSeeingTargets();
				if (qtyShotOnTarget != 0) {
					for (int idxShot = 0; idxShot <= qtyShotOnTarget; idxShot++) {
						try {
							processAction(Action.FIRE, tankRun);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

		@Override
		public Object call() throws Exception {

			BattleFieldObjects headquartersObj = battleField.scanQuadrant(battleField.getHeadquarters()[0], battleField.getHeadquarters()[1]);

			while (gameMode == GameMode.GAME) {

					if (!tankRun.isDestroyed()) {

						ifSeeingTargetThenShot();

						try {
							processAction(tankRun.setUp(), tankRun);
						} catch (Exception e) {
							e.printStackTrace();
						}

						ifSeeingTargetThenShot();

						if (((AbstractTank) tankRun).isHandControl() && keyMovePressed.get() && (timeAddMove.get() <= System.currentTimeMillis())) {
							for (int i = 0; i < 10; i++) {
								player.setActionTank(Action.MOVE, false);
							}
							timeAddMove.set(Long.MAX_VALUE);
						}

					} else {
						return null;
					}

				BattleFieldObject headquartersObject =  battleField.scanQuadrant(battleField.getHeadquarters()[0], battleField.getHeadquarters()[1]);
				if(headquartersObject.isDestroyed() || headquartersObject instanceof Empty || (tankRun.isDestroyed() && ((AbstractTank)tankRun).getBehavior() == Behavior.DEFENDER)){
					serviceThread.shutdownNow();
					switchingPanels(GameMode.RESULT);
				}

			}

			return null;
		}

	}

	public void runTheGame() {

		timeCreateDefender = 0;

		Tank defender = null;
		Tank agressor = null;

		defenders.clear();
		agressors.clear();
		shotsBullet.clear();

		if (serviceThread.isShutdown()){
			serviceThread = Executors.newCachedThreadPool();//newFixedThreadPool(5);
		}

		if (!flRepeaatGame) {
			defender = new T34(battleField, Behavior.DEFENDER);
			((AbstractTank) defender).setHandControl(true);
			defender.setTargetForTank();
			player = defender;

			agressor = getNewAgressor();
			agressor.setTargetForTank();
		} else {
			defender = getTankFromLog("DEFENDER", mapLog);
			player = defender;

			agressor = getTankFromLog("AGRESSOR", mapLog);
			timeCreateNewAgressor = 0;

			if (agressor == null || defender == null) {
				return;
			}

		}

		RunTheGameTank runTheGameDefender = new RunTheGameTank(defender);
		RunTheGameTank runTheGameAgressor = new RunTheGameTank(agressor);

		serviceThread.submit(runTheGameDefender);
		serviceThread.submit(runTheGameAgressor);

	}

	private void processAction(Action a, final Tank tank) throws Exception {
		if (a == Action.MOVE) {
			processMove(tank);
		} else if (a == Action.FIRE) {
			if (((AbstractTank) tank).getBehavior() == Behavior.AGRESSOR) {
				sleep(500);
			}

			Bullet newBullet = tank.fire();
			shotsBullet.add(newBullet);
			bulletQueue.add(newBullet);
			new Thread(new Runnable() {
				@Override
				public void run() {
					Bullet currentBullet = bulletQueue.poll();

					try {
						processFire(currentBullet);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			//}

		}
	}

	public AbstractTank getTank(){

		for (Tank curTank : defenders){
			if (curTank.isDestroyed()) continue;
			return (AbstractTank)curTank;
		}

		return null;
	}

	public BattleField getBattleField(){
		return battleField;
	}

	public Bullet getBullet(){
		//return bullet;
		return bulletQueue.poll();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (gameMode == GameMode.GAME){

			battleField.draw(g);

			if (timeCreateNewAgressor != 0 && System.currentTimeMillis() >= timeCreateNewAgressor) {
				Tank agressor = getNewAgressor();
				RunTheGameTank runTheGameAgressor = new RunTheGameTank(agressor);
				serviceThread.submit(runTheGameAgressor);
			} else if (timeCreateDefender != 0 && System.currentTimeMillis() >= timeCreateDefender){
				timeCreateDefender = 0;
				Tank defender = new T34(battleField, Behavior.DEFENDER);
				RunTheGameTank runTheGameDefender = new RunTheGameTank(defender);
				serviceThread.submit(runTheGameDefender);
			}

			for (Tank curTank : defenders){
				if (curTank.isDestroyed()) continue;
				curTank.draw(g);
			}

			for (Tank curTank : agressors){
				if (curTank.isDestroyed()) {
					continue;
				}
				curTank.draw(g);
			}

			for (Bullet currentBullet : shotsBullet) {
				if (!currentBullet.isDestroyed()) {
					currentBullet.draw(g);
				}

			}

		}

	}

	public void processFire(Bullet bullet) throws Exception {

		logAction("Action", bullet.getTankParent(), Action.FIRE, bullet.getTankParent().getDirection());

		int count = 0;

		//Cikl obespechivait plavnoe dvizhenie bullet
		while (count < battleField.getBfWidth()/bullet.getSpeed()) {

			if (bullet.getDirection() == Direction.UP) {
				bullet.updateY((-1)*bullet.getSpeed());
			}else if (bullet.getDirection() == Direction.DOWN) {
				bullet.updateY(bullet.getSpeed());
			}else if (bullet.getDirection() == Direction.LEFT) {
				bullet.updateX((-1)*bullet.getSpeed());
			}else if (bullet.getDirection() == Direction.RIGHT) {
				bullet.updateX(bullet.getSpeed());
			}

			count++;

			shotsBullet.add(bullet);

			if (processInterception(bullet)){
				bullet.destroy();
				break;
			}

			sleep(bullet.getSpeed());
		}

	}

	private void sleep(int millis){

		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			//ignore
		}

	}

	private boolean processInterception(Bullet bullet){

		boolean result = false;

		//proveryaem tol'ko esli pulya v predelah boya
		String YX = "0_0";
		if ((bullet.getX() >= 0 && bullet.getX() < battleField.getBfHeigt()) && (bullet.getY() >= 0 && bullet.getY() < battleField.getBfWidth())) {
			YX = getQuadrant(bullet.getX(), bullet.getY());//"0_8"
		} else {
			return true;
		}

		int symv_ = YX.indexOf("_");
		int y = Integer.valueOf(YX.substring(0, symv_));
		int x = Integer.valueOf(YX.substring(symv_ + 1));

		BattleFieldObjects valQuadrant = battleField.scanQuadrant(y, x);

		if (valQuadrant instanceof Brick || valQuadrant instanceof Eagle) {

			battleField.updateQuadrant(y, x, new Empty(x,y));

			return true;
		} else if (valQuadrant instanceof Rock){
			return true;
		}

		//Otrabotka popadaniya v tank
		if (bulletCrossesTank(bullet) == true){
			return true;
		}

		if (bulletCrossesBullet(bullet) == true) {
			return true;
		}
		 
		return result;
		
	}

	private void destroy(Bullet bullet, AbstractTank tank) {
		if (bullet != null) {
			bullet.destroy();
		}

		if (tank != null) {
			tank.destroy();

			if (tank.getBehavior() == Behavior.AGRESSOR){
				timeCreateNewAgressor = System.currentTimeMillis() + 1000*3;
			} else {
				timeCreateDefender = System.currentTimeMillis() + 1000*3;
			}
		}

	}

	private boolean bulletCrossesTank(Bullet bullet_) {
		
		boolean result = false;
		
		if(bullet_ == null){
			return false;
		}
		
		int xBullet = bullet_.getX();
		int yBullet = bullet_.getY();

		//bullet_.tankParent.getTarget();
		AbstractTank currentAT  = bullet_.getTankParent();
		Object [] targets 		= battleField.getTaregetsObject(currentAT);

		int yTankTarget = -100;
		int xTankTarget = -100;

		//int xTankDefender = defender.getX();
		//int yTankDefender = defender.getY();

		for (Object target : targets){

			if (!(target instanceof AbstractTank)) continue;

			yTankTarget = ((AbstractTank) target).getY();
			xTankTarget = ((AbstractTank) target).getX();

			byte hitX = 0;
			byte hitY = 0;

			if (((xBullet >= xTankTarget) && (xBullet <= xTankTarget+64)) || ((xBullet+14 >= xTankTarget) && (xBullet+14 <= xTankTarget+64))){
				hitX = 1;
			}

			if (((yBullet >= yTankTarget) && (yBullet <= yTankTarget+64)) || ((yBullet+14 >= yTankTarget) && (yBullet+14 <= yTankTarget+64))){
				hitY = 1;
			}


			if(hitX + hitY >= 2){
				if ((Tank)bullet_.getTankParent() == (Tank)target){
					result = false;
				} else {
					result = true;
				}

			}

			if (result == true){
				int armor = 0;
				if (target instanceof Tiger) {
					armor = ((Tiger) target).getArmor();
					((Tiger) target).setArmor(armor - 1);
				}

				if (armor == 0) {
					destroy(bullet_, (AbstractTank) target);
					if (currentAT.getBehavior() == Behavior.AGRESSOR) {
						currentAT.setTarget(battleField.getHeadquarters());
					} else{
						//currentAT.setTarget(battleField.getQuadrantAgressor());
					}

				}
			}

			return result;

		}

		return result;
	}

	private boolean bulletCrossesBullet(Bullet bullet){

		boolean result = false;

		if (bullet == null){
			return false;
		}

		int xBullet = bullet.getX();
		int yBullet = bullet.getY();

		Object[] targetsBullet = shotsBullet.toArray();

		for (Object currentObject : targetsBullet){

			Bullet currentBullet = (Bullet)currentObject;

			if (currentBullet.isDestroyed()) {
				shotsBullet.remove(targetsBullet);
				continue;
			}
			if (currentBullet == bullet) continue;

			int yCurrentBullet = currentBullet.getY();
			int xCurrentBullet = currentBullet.getX();

			byte hitX = 0;
			byte hitY = 0;

			if (((xBullet >= xCurrentBullet) && (xBullet <= xCurrentBullet+64)) || ((xBullet+14 >= xCurrentBullet) && (xBullet+14 <= xCurrentBullet+64))){
				hitX = 1;
			}

			if (((yBullet >= yCurrentBullet) && (yBullet <= yCurrentBullet+64)) || ((yBullet+14 >= yCurrentBullet) && (yBullet+14 <= yCurrentBullet+64))){
				hitY = 1;
			}

			if(hitX + hitY >= 2){
				result = true;
				currentBullet.destroy();
			}

			return result;

		}

		return result;

	}

	public String getQuadrant(int x, int y){

		Tank defender = getDefender();

		return "" + y/defender.step +  "_" + x/defender.step;
		
	}

	public int [] getQuadrantXY (int y, int x) {
				
		int [] XY = new int [2];
		
		XY[0] = y*64;
		XY[1] = x*64;
		
		
		return XY;
	}

//	public void processTurn(AbstractTank tank) {
//
//		//this.defender = tank;
//
//		repaint();
//
//	}

	public void processMove(Tank tank) throws Exception {
		
		//this.defender = tank;

		logAction("Action", tank, Action.MOVE, tank.getDirection());

		processTurn(tank);
		
		int covered = 0;

		for (int i = 0; i < tank.getMovePath(); i++) {

			if (!tank.moznoSdelatShag(tank.getDirection())) {

				if (!((AbstractTank)tank).isHandControl()) {
					processAction(Action.FIRE, tank);
					sleep(500);
				}

				if (!tank.moznoSdelatShag(tank.getDirection())) {
					return;
				}

			}

			//Cikl obespechivait plavnoe dvizhenie tank
			while (covered < tank.step) {

				if (tank.getDirection() == Direction.getDirectoinOfId(4)) {
					// 4 right
					//tank.tankX += tank.shortStep;
					tank.updateX(tank.shortStep);

				} else if (tank.getDirection() == Direction.getDirectoinOfId(3)) {
					// 3 left
					//tank.tankX -= tank.shortStep;
					tank.updateX((-1) * tank.shortStep);

				} else if (tank.getDirection() == Direction.getDirectoinOfId(1)) {
					// 1 up
					//tank.tankY -= tank.shortStep;
					tank.updateY((-1) * tank.shortStep);

				} else if (tank.getDirection() == Direction.getDirectoinOfId(2)) {
					// 2 down
					//tank.tankY += tank.shortStep;
					tank.updateY(tank.shortStep);
				}

				covered++;

				repaint();

				sleep((int) (15 * (1 / ((double) tank.getSpeed() / 15))));
			}
		}			
		
	}

	private void processTurn(Tank tank) throws Exception {
		repaint();
		logAction("Action", tank, Action.TURN, tank.getDirection());
	}

	private void logAction(String typeAction,Tank tank, Action action, Direction direction) throws IOException {

		FileWriter logFileWriter = new FileWriter(LOG_NAME,true);
		//myString.split(" ");//в массив с разделителями

		if (typeAction.equals("Action")) {
			AbstractTank logTank = (AbstractTank)tank;
			logFileWriter.write(typeAction + ";");
			logFileWriter.write(logTank.getBehavior().toString() + ";" + action.toString() + ";" + direction.toString()+ ";");
			logFileWriter.write(logTank.getYXnow()[0]+ ";");//Y
			logFileWriter.write(logTank.getYXnow()[1]+ ";");
			logFileWriter.write(logTank.getClass().getSimpleName()+ ";");
			logFileWriter.write((int)'\n');
			logFileWriter.flush();
			logFileWriter.close();
		}else if (typeAction.equals("GENERATE")){
			AbstractTank logTank = (AbstractTank)tank;
			logFileWriter.write(typeAction + ";");
			logFileWriter.write(logTank.getBehavior().toString() + ";" + action.toString() + ";" + direction.toString()+ ";");
			logFileWriter.write(logTank.getYXnow()[0]+ ";");//Y
			logFileWriter.write(logTank.getYXnow()[1]+ ";");
			logFileWriter.write(logTank.getClass().getSimpleName()+ ";");
			logFileWriter.write((int)'\n');
			logFileWriter.flush();
			logFileWriter.close();
		}

	}

	public void moveToQuadrant(int y, int x, Tank tank) throws Exception {

		tank.setProcessMoveToQuadrant(y, x, true, null, false);

	}

	public int[] getQuadrantDefender(){
		Tank defender = getDefender();
		if (defender != null && defender.isDestroyed() != true){
			return defender.getYXnow();
		} else {
			return battleField.getHeadquarters();
		}

	}

	public int[] getQuadrantAgressor(){
		Tank agressor = getAgressor();
		if (agressor != null && agressor.isDestroyed() != true){
			return agressor.getYXnow();
		} else {
			return new int []{8,2};
		}

	}

	public Tank getDefender(){

		if (defenders == null) return null;

		for (Tank curTank : defenders) {
			if (curTank.isDestroyed()) continue;

			return curTank;
		}

		return null;
//		return  defender.isDestroyed()?null:defender;
	}

	public Tank getAgressor(){

		if (agressors == null) return null;

		for (Tank curTank : agressors) {
			if (curTank.isDestroyed()) continue;

			return curTank;
		}

		return null;
//		return agressor;
	}

	private class MyDispatcher implements KeyEventDispatcher {

		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			if (e.getID() == KeyEvent.KEY_PRESSED) {
				//add action turn
				int codeKey = e.getKeyCode();
				if (codeKey == KeyEvent.VK_LEFT) {
					player.setActionTank(Direction.LEFT, false);
					keyMovePressed.set(true);
				} else if(codeKey == KeyEvent.VK_RIGHT){
					player.setActionTank(Direction.RIGHT, false);
					keyMovePressed.set(true);
				} else if (codeKey == KeyEvent.VK_DOWN){
					player.setActionTank(Direction.DOWN, false);
					keyMovePressed.set(true);
				}else if (codeKey == KeyEvent.VK_UP){
					player.setActionTank(Direction.UP, false);
					keyMovePressed.set(true);
				}else if (codeKey == KeyEvent.VK_SPACE){

					if (((AbstractTank)player).isAllowToShot()) {
						((AbstractTank)player).setAllowToShot(false);
						player.setActionTank(Action.FIRE, true);
					}
				} else {

				}

				if (codeKey == KeyEvent.VK_LEFT || codeKey == KeyEvent.VK_RIGHT || codeKey == KeyEvent.VK_DOWN ||codeKey == KeyEvent.VK_UP) {
					timeAddMove.set(System.currentTimeMillis() + 300);
				}


			} else if (e.getID() == KeyEvent.KEY_RELEASED) {

				int codeKey = e.getKeyCode();
				if (codeKey == KeyEvent.VK_LEFT || codeKey == KeyEvent.VK_RIGHT || codeKey == KeyEvent.VK_DOWN ||codeKey == KeyEvent.VK_UP) {
					keyMovePressed.set(false);
					timeAddMove.set(Long.MAX_VALUE);
					player.cleanAction();

					if (keyMovePressed.get()) {
						timeAddMove.set(System.currentTimeMillis() + 100);
					}

				}

			} else if (e.getID() == KeyEvent.KEY_TYPED) {
					int o = 0;
			}
			return false;
		}
	}

}
