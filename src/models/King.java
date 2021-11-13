import models.Piece;
import models.Square;

public class King extends Piece {
  
    public Collection<Move> getAllMoves(final Square[][] gameboard) {
        
        Collection<Move> legalMoves = new ArrayList<>();
        
        legalMoves.add(new Move(super.rank+1, super.file, this));
        legalMoves.add(new Move(super.rank-1, super.file, this));

        legalMoves.add(new Move(super.rank, super.file+1, this));
        legalMoves.add(new Move(super.rank, super.file-1, this));

        legalMoves.add(new Move(super.rank+1, super.file+1, this));
        legalMoves.add(new Move(super.rank-1, super.file-1, this));

        legalMoves.add(new Move(super.rank+1, super.file-1, this));
        legalMoves.add(new Move(super.rank-1, super.file+1, this));
        
        return legalMoves;
    }
  
    
}
