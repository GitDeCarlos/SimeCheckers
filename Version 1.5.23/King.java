import javax.swing.ImageIcon;

public class King extends Piece{
	private boolean side;
	private final ImageIcon red = new ImageIcon(CheckerP.class.getResource("redK.png"));
	private final ImageIcon grn = new ImageIcon(CheckerP.class.getResource("greenK.png"));
	private int kills;
	private String name;
	
	
	public King(boolean color, int k, String n){
		side = color;
		kills = k;
		name = n;
	}
	
	public boolean isKing(){
		return true;
	}
	
	public int getKills(){
		return kills;
	}
	
	public String getName(){
		return name;
	}
	
	public void addKill(){
		kills = kills+1;
	}
	
	public ImageIcon getChecker(){
		if(side)
			return red;
		else return grn;
	}
	
	public boolean whichSide(){
		return side;
	}
	
	public boolean legalMove(Tile a, Tile b, Tile[][] bd){
		if(Math.abs(b.getCol() - a.getCol()) == 1 && a.getRow()-1 == b.getRow() && !b.present())
			return true;
		if(Math.abs(b.getCol() - a.getCol()) == 1 && a.getRow()+1 == b.getRow() && !b.present())
			return true;
		if(a.getCol()+2 == b.getCol() && a.getRow()-2 == b.getRow() && !b.present()){
			if(bd[a.getCol()+1][a.getRow()-1].present() && 
					bd[a.getCol()+1][a.getRow()-1].getPiece().whichSide() != a.getPiece().whichSide()){
				bd[a.getCol()+1][a.getRow()-1].removePiece(); addKill();
				return true;
			}else return false;
				
		}
		if(a.getCol()-2 == b.getCol() && a.getRow()-2 == b.getRow() && !b.present()){
			if(bd[a.getCol()-1][a.getRow()-1].present() && 
					bd[a.getCol()-1][a.getRow()-1].getPiece().whichSide() != a.getPiece().whichSide()){
				bd[a.getCol()-1][a.getRow()-1].removePiece(); addKill();
				return true;
			}else return false;
		}
		if(a.getCol()+2 == b.getCol() && a.getRow()+2 == b.getRow() && !b.present()){
			if(bd[a.getCol()+1][a.getRow()+1].present() && 
					bd[a.getCol()+1][a.getRow()+1].getPiece().whichSide() != a.getPiece().whichSide()){
				bd[a.getCol()+1][a.getRow()+1].removePiece(); addKill();
				return true;
			}else return false;
				
		}
		if(a.getCol()-2 == b.getCol() && a.getRow()+2 == b.getRow() && !b.present()){
			if(bd[a.getCol()-1][a.getRow()+1].present() && 
					bd[a.getCol()-1][a.getRow()+1].getPiece().whichSide() != a.getPiece().whichSide()){
				bd[a.getCol()-1][a.getRow()+1].removePiece(); addKill();
				return true;
			}else return false;
		}
		else return false;
		
	}
}
