package present;
import javax.swing.*;

public class Programmer {

	public static void main(String[] args) {
		Model data = new Model();
		View view = new View(data);
		Controller game = new Controller(data,view);
		Music music = new Music(data, view);
	}
}
