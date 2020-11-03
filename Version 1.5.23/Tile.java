
import java.awt.Color;

import javax.swing.*;

public class Tile extends JLabel{
	private int col;
	private int row;
	private Piece piece;
	
	public Tile(int a, int b){
		col = a;
		row = b;
	}
	
	public int getCol(){
		return col;
	}
	
	public int getRow(){
		return row;
	}
	
	public void setPiece(Piece p){
		piece = p;
		setIcon(p.getChecker());
	}
	
	public Piece getPiece(){
		return piece;
	}
	
	public void removePiece(){
		piece = null;
		setIcon(null);
	}
	
	public void placePiece(Piece p, Tile t){		
		if(getRow() == 0 && p.whichSide()){
			setPiece(new King(true, t.piece.getKills(), t.piece.getName()));
			t.removePiece();
		}
		
		else if(getRow() == 7 && !p.whichSide()){
			setPiece(new King(false,t.piece.getKills(), t.piece.getName()));
			t.removePiece();
		}
		
		else{
			t.removePiece();
			setPiece(p);
		}
	}
	
	public void highlight(Tile bd[][]){
		int col = getCol(); int row = getRow();
		setBorder(BorderFactory.createLineBorder(new Color(121,147,198), 3));
		
		if(getPiece().isKing()){
			if(col < 7 && row > 0 ){
				if(!bd[col+1][row-1].present())
					bd[col+1][row-1].setBorder(BorderFactory.createLineBorder(new Color(255,207,94), 3));
				else if(col < 6 && row > 1)
					if(bd[col+1][row-1].present() && bd[col+1][row-1].getPiece().whichSide() != getPiece().whichSide() && !bd[col+2][row-2].present())
						bd[col+2][row-2].setBorder(BorderFactory.createLineBorder(new Color(255,207,94), 3));
			}
			if(col > 0 && row > 0){
				if(!bd[col-1][row-1].present())
					bd[col-1][row-1].setBorder(BorderFactory.createLineBorder(new Color(255,207,94), 3));
				else if(col > 1 && row > 1)
					if(bd[col-1][row-1].present() && bd[col-1][row-1].getPiece().whichSide() != getPiece().whichSide() && !bd[col-2][row-2].present())
						bd[col-2][row-2].setBorder(BorderFactory.createLineBorder(new Color(255,207,94), 3));
			}
			if(col < 7 && row < 7 ){
				if(!bd[col+1][row+1].present())
					bd[col+1][row+1].setBorder(BorderFactory.createLineBorder(new Color(255,207,94), 3));
				else if(col < 6 && row < 6)
					if(bd[col+1][row+1].present() && bd[col+1][row+1].getPiece().whichSide() != getPiece().whichSide() && !bd[col+2][row+2].present())
						bd[col+2][row+2].setBorder(BorderFactory.createLineBorder(new Color(255,207,94), 3));
			}
			if(col > 0 && row < 7){
				if(!bd[col-1][row+1].present())
					bd[col-1][row+1].setBorder(BorderFactory.createLineBorder(new Color(255,207,94), 3));
				else if(col > 1 && row < 6)
					if(bd[col-1][row+1].present() && bd[col-1][row+1].getPiece().whichSide() != getPiece().whichSide() && !bd[col-2][row+2].present())
						bd[col-2][row+2].setBorder(BorderFactory.createLineBorder(new Color(255,207,94), 3));
			}
		}
		if(getPiece().whichSide()){
			if(col < 7 && row > 0 ){
				if(!bd[col+1][row-1].present())
					bd[col+1][row-1].setBorder(BorderFactory.createLineBorder(new Color(255,207,94), 3));
				else if(col < 6 && row > 1)
					if(bd[col+1][row-1].present() && bd[col+1][row-1].getPiece().whichSide() != getPiece().whichSide() && !bd[col+2][row-2].present())
						bd[col+2][row-2].setBorder(BorderFactory.createLineBorder(new Color(255,207,94), 3));
			}
			if(col > 0 && row > 0){
				if(!bd[col-1][row-1].present())
					bd[col-1][row-1].setBorder(BorderFactory.createLineBorder(new Color(255,207,94), 3));
				else if(col > 1 && row > 1)
					if(bd[col-1][row-1].present() && bd[col-1][row-1].getPiece().whichSide() != getPiece().whichSide() && !bd[col-2][row-2].present())
						bd[col-2][row-2].setBorder(BorderFactory.createLineBorder(new Color(255,207,94), 3));
			}
		}
		else if(!getPiece().whichSide()){
			if(col < 7 && row < 7 ){
				if(!bd[col+1][row+1].present())
					bd[col+1][row+1].setBorder(BorderFactory.createLineBorder(new Color(255,207,94), 3));
				else if(col < 6 && row < 6)
					if(bd[col+1][row+1].present() && bd[col+1][row+1].getPiece().whichSide() != getPiece().whichSide() && !bd[col+2][row+2].present())
						bd[col+2][row+2].setBorder(BorderFactory.createLineBorder(new Color(255,207,94), 3));
			}
			if(col > 0 && row < 7){
				if(!bd[col-1][row+1].present())
					bd[col-1][row+1].setBorder(BorderFactory.createLineBorder(new Color(255,207,94), 3));
				else if(col > 1 && row < 6)
					if(bd[col-1][row+1].present() && bd[col-1][row+1].getPiece().whichSide() != getPiece().whichSide() && !bd[col-2][row+2].present())
						bd[col-2][row+2].setBorder(BorderFactory.createLineBorder(new Color(255,207,94), 3));
			}
		}
		repaint();
	}
	
	public void clearHighlights(Tile[][] bd){
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				bd[x][y].setBorder(null);
				bd[x][y].setBorder(BorderFactory.createRaisedBevelBorder());
			}
		}
	}
	
	public boolean present(){
		if(piece != null)
			return true;
		else return false;
	}
	
}
