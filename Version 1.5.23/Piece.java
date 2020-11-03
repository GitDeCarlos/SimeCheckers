import javax.swing.ImageIcon;

public class Piece {
	private boolean side;
	private int kills;
	
	public Piece(){
		//null lol
	}
	
	public boolean isKing(){
		return false;
	}
	
	public void addKill(){
		kills = kills + 1;
	}
	
	public String getName(){
	 	return null;
	}
	
	public int getKills(){
		return kills;
	}
	
	public ImageIcon getChecker(){
		if(side)
			return null;
		else return null;
	}
	
	public boolean whichSide(){
		return side;
	}
	
	public boolean legalMove(Tile a, Tile b, Tile[][] c){
		return false;
	}
}
