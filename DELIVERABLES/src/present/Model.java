package present;

import java.util.ArrayList;
import java.util.Arrays;

public class Model {

	private Hero hero = new Hero();
	private Cat cat = new Cat(hero);
	private PickUps pickup = new PickUps();
	private Score records = new Score();
	
	Enemy enemy = new Enemy(hero);

	private Boss boss = new Boss(hero, 1376/2,768/2);

	private static final int maxKnights = 7;
	private static final int maxBombs = 4;
	private static final int maxDogs = 15;


	// Knight Army
	private ArrayList<Knight> knights = new ArrayList<Knight>();
	private Knight knight0 = new Knight(hero,1376/2,-300,3,0,0);

	private Bracelet brace = new Bracelet(getHero());
	Enemy[] enemyArmy = new Enemy[5];
	boolean ready = true;
	boolean loaded = false;
	private Maps map = new Maps();
	private Character character = new Character();
	Entities entity = new Entities();
	

	// Create Enemies
	ArrayList<Enemy> enemyArmyList = new ArrayList<Enemy>();



	private FireBlast tempFireShot;

	private IceBlast tempIceShot;

	// Bomb Army
	private ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	private Bomb bomb0 = new Bomb(getHero(),600,100,0,0,700,0); // CHANGES
	private Bomb bomb1 = new Bomb(getHero(),400,600,1,800,0,2);
	private Bomb bomb2 = new Bomb(getHero(),800,600,0,0,100,1);
	private Bomb bomb3 = new Bomb(getHero(),1200,100,1,300,0,3);
	private Bomb bomb4 = new Bomb(getHero(),200,400,0,0,600,2);
	private Bomb bomb5 = new Bomb(getHero(),700,345,1,200,0,3);
	//private Bomb bomb6 = new Bomb(getHero(),245,400,0,0,600,2);
	//private Bomb bomb7 = new Bomb(getHero(),700,345,1,200,0,3);


	private ArrayList<Dog> dogs = new ArrayList<Dog>();
	private Dog dog0 = new Dog(hero,200,0,0);
	private Dog dog1 = new Dog(hero,300,0,1);
	private Dog dog2 = new Dog(hero,400,300,0);
	private Dog dog3 = new Dog(hero,500,200,1);
	private Dog dog4 = new Dog(hero,400,100,0);
	private Dog dog5 = new Dog(hero, 600, 400, 0);
	private Dog dog6 = new Dog(hero, 700, 600,1);
	private Dog dog7 = new Dog(hero, 900, 200,0);
	private Dog dog8 = new Dog(hero, 100, 600, 0);
	private Dog dog9 = new Dog(hero, 736, 678, 1);
	private Dog dog10 = new Dog(hero, 600, 600, 0);
	private Dog dog11 = new Dog(hero, 700, 700, 1);
	private Dog dog12 = new Dog(hero, 1000, 400, 1);
	private Dog dog13 = new Dog(hero, 857, 233, 0);

	//protected 
	int stage;

	protected int CharX, CharY = 0;

	public Model() {
		stage = 0;
		bombsList();
		knightsList();
		dogsList();
		enemyArrayList();
	}

	public void setStage(int newStage) {
		stage = newStage;
		if(stage > 0) {
			map.setStage(stage);
			if(Maps.getLoadComplete() == 2 ) {
				map.loadLevelCollisions();
				entity.setLoad(false);
			}
		}
	}
	
	@SuppressWarnings("static-access")
	public void loadPickUps() {
		if (stage == 1) {
			if (pickup.isLoaded() == false) {
				pickup.drawOrange();
				pickup.drawBlack();
				pickup.drawWhite();
				pickup.drawBlue();
				pickup.drawRed();
				pickup.setLoaded(true);
			}
		}
		if (stage == 2) {
			if (pickup.isLoaded() == true) {
				pickup.drawOrange();
				pickup.drawBlack();
				pickup.drawBlue();
				pickup.drawRed();
				pickup.setLoaded(false);
			}
		}
		if  (stage == 3) {
			if (pickup.isLoaded() == false) {
				pickup.drawOrange();
				pickup.drawBlack();
				pickup.drawBlue();
				pickup.drawRed();
				pickup.setLoaded(true);
			}
		}
		if (stage == 4) {
			if (pickup.isLoaded() == false) {
				pickup.drawOrange();
				pickup.drawBlack();
				pickup.drawBlue();
				pickup.drawRed();
				pickup.setLoaded(true);
			}
		}
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public Cat getCat() {
		return cat;
	}

	public void updateFireBlast() {
		for (int i = 0; i < hero.getFireBlast().size(); i++) {
			setTempFireShot(hero.getFireBlast().get(i));
			ready = false;
			if (tempFireShot.checkPowerUpRange()) {
				ready = true;
				removeFire(getTempFireShot());
			}
			getTempFireShot().update();
		}
	}

	public void updateIceBlast() {
		for (int i = 0; i < hero.getIceBlast().size(); i++) {
			setTempIceShot(hero.getIceBlast().get(i));
			ready = false;
			if (tempIceShot.checkPowerUpRange()) {
				ready = true;
				removeIce(getTempIceShot());
			}
			getTempIceShot().update();
		}

	}


	public void addIce(IceBlast ice) {
		hero.getIceBlast().add(ice);
	}

	public void removeIce(IceBlast ice) {
		hero.getIceBlast().remove(ice);
	}

	public void addFire(FireBlast fire) {
		hero.getFireBlast().add(fire);
	}

	public void removeFire(FireBlast fire) {
		hero.getFireBlast().remove(fire);
	}

	public void setCat(Cat cat) {
		this.cat = cat;
	}




	public Enemy getEnemy() {
		return enemy;
	}
	public Bracelet getBrace() {
		return brace;
	}

	public void setBrace(Bracelet brace) {
		this.brace = brace;
	}

	public Maps getMap() {
		return map;
	}



	public void enemyArrayList() {
		enemyArmyList.addAll(bombs);
		enemyArmyList.addAll(knights);
		enemyArmyList.add(boss);
		enemyArmyList.addAll(dogs);
	}

	public void bombsList() {
		bombs.add(bomb0);
		bombs.add(bomb1);
		bombs.add(bomb2);
		bombs.add(bomb3);
		bombs.add(bomb4);
		bombs.add(bomb5);

	}

	public void knightsList() {
		knights.add(knight0);
	}

	public void dogsList() {
		dogs.add(dog0);
		dogs.add(dog1);
		dogs.add(dog2);
		dogs.add(dog3);
		dogs.add(dog4);
		dogs.add(dog5);
		dogs.add(dog6);
		dogs.add(dog7);
		dogs.add(dog8);
		dogs.add(dog9);
		dogs.add(dog10);
		dogs.add(dog11);
		dogs.add(dog12);
		dogs.add(dog13);


	}

	public void reloadEnemies() {
		enemyArmyList.clear();
		enemyArrayList();
	}

	public FireBlast getTempFireShot() {
		return tempFireShot;
	}

	public void setTempFireShot(FireBlast tempFireShot) {
		this.tempFireShot = tempFireShot;
	}

	public IceBlast getTempIceShot() {
		return tempIceShot;
	}

	public void setTempIceShot(IceBlast tempIceShot) {
		this.tempIceShot = tempIceShot;
	}

	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}

	public int getStage() {
		return this.stage;
	}

	public Bomb getBomb0() {
		return bomb0;
	}

	public PickUps getPickup() {
		return pickup;
	}

	public void setPickup(PickUps pickup) {
		this.pickup = pickup;
	}

	public Score getRecords() {
		return records;
	}

	public void setRecords(Score records) {
		this.records = records;
	}
	public void setBomb0(Bomb bomb0) {
		this.bomb0 = bomb0;
	}

	public ArrayList<Bomb> getBombs() {
		return bombs;
	}

	public void setBombs(ArrayList<Bomb> bombs) {
		this.bombs = bombs;
	}

	public ArrayList<Knight> getKnights() {
		return knights;
	}

	public void setKnights(ArrayList<Knight> knights) {
		this.knights = knights;
	}

	public ArrayList<Dog> getDogs() {
		return dogs;
	}

	public void setDogs(ArrayList<Dog> dogs) {
		this.dogs = dogs;
	}
	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public static int getMaxknights() {
		return maxKnights;
	}

	public static int getMaxbombs() {
		return maxBombs;
	}

	public static int getMaxdogs() {
		return maxDogs;
	}
	


}