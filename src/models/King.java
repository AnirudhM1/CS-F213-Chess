import models.Piece;
import models.Square;

public class King extends Piece {
  
    public King(String white)
    {
        super(white);
    }
  
  
    @Override
    public boolean canMove(Square Square, Piece start, Piece end)
    {

        if (end.getColor == start.getColor) {
            return false;
        }
  
        int x = Math.abs(start.getRank- end.getRank);
        int y = Math.abs(start.getfile - end.getfile);
        if ((x<=1) & (y<=1)) {
            return true;
        }
  
        return false;
    }
  
    
}
