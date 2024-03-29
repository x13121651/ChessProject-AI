import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/*
    This class can be used as a starting point for creating your Chess game project. The only piece that
	has been coded is a white pawn...a lot done, more to do!
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
    int startX;
    int startY;
    int initialX;
    int initialY;
    JPanel panels;
    JLabel pieces;


    public ChessProject() {
        Dimension boardSize = new Dimension(600, 600);

        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane 
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground(i % 2 == 0 ? Color.white : Color.gray);
            else
                square.setBackground(i % 2 == 0 ? Color.gray : Color.white);
        }

        // Setting up the Initial Chess board.
        for (int i = 8; i < 16; i++) {
            pieces = new JLabel(new ImageIcon("WhitePawn.png"));
            panels = (JPanel) chessBoard.getComponent(i);
            panels.add(pieces);
        }
        pieces = new JLabel(new ImageIcon("WhiteRook.png"));
        panels = (JPanel) chessBoard.getComponent(0);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
        panels = (JPanel) chessBoard.getComponent(1);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
        panels = (JPanel) chessBoard.getComponent(6);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteBishup.png"));
        panels = (JPanel) chessBoard.getComponent(2);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteBishup.png"));
        panels = (JPanel) chessBoard.getComponent(5);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKing.png"));
        panels = (JPanel) chessBoard.getComponent(3);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
        panels = (JPanel) chessBoard.getComponent(4);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteRook.png"));
        panels = (JPanel) chessBoard.getComponent(7);
        panels.add(pieces);
        for (int i = 48; i < 56; i++) {
            pieces = new JLabel(new ImageIcon("BlackPawn.png"));
            panels = (JPanel) chessBoard.getComponent(i);
            panels.add(pieces);
        }
        pieces = new JLabel(new ImageIcon("BlackRook.png"));
        panels = (JPanel) chessBoard.getComponent(56);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKnight.png"));
        panels = (JPanel) chessBoard.getComponent(57);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKnight.png"));
        panels = (JPanel) chessBoard.getComponent(62);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackBishup.png"));
        panels = (JPanel) chessBoard.getComponent(58);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackBishup.png"));
        panels = (JPanel) chessBoard.getComponent(61);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKing.png"));
        panels = (JPanel) chessBoard.getComponent(59);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackQueen.png"));
        panels = (JPanel) chessBoard.getComponent(60);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackRook.png"));
        panels = (JPanel) chessBoard.getComponent(63);
        panels.add(pieces);
    }

    /*
        This method checks if there is a piece present on a particular square.
    */
    private Boolean piecePresent(int x, int y) {
        Component c = chessBoard.findComponentAt(x, y);
        if (c instanceof JPanel) {
            return false;
        } else {
            return true;
        }
    }

    /*
        This is a method to check if a piece is a Black piece.
    */
    private Boolean checkWhiteOponent(int newX, int newY) {
        Boolean oponent;
        Component c1 = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) c1;
        String tmp1 = awaitingPiece.getIcon().toString();
        oponent = ((tmp1.contains("Black")));
        return oponent;
    }

    /*
       This is a method to check if a piece is Black.
   */
    private Boolean checkBlackOponent(int newX, int newY) {
        Boolean oponent;
        Component c1 = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) c1;
        String tmp1 = awaitingPiece.getIcon().toString();
        oponent = ((tmp1.contains("White")));
        return oponent;
    }

    /*
        This method is called when we press the Mouse. So we need to find out what piece we have
        selected. We may also not have selected a piece!
    */
    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel)
            return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel) c;
        initialX = e.getX();
        initialY = e.getY();
        startX = (e.getX() / 75);
        startY = (e.getY() / 75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    /*
       This method is used when the Mouse is released...we need to make sure the move was valid before
       putting the piece back on the board.
   */
    public void mouseReleased(MouseEvent e) {


        if (chessPiece == null) return;

        chessPiece.setVisible(false);
        Boolean success = false;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        String tmp = chessPiece.getIcon().toString();
        String pieceName = tmp.substring(0, (tmp.length() - 4));


        /*
  This code helps us to understand what is happening when a user starts
  to move pieces around the board. We are using the standard output to show
  - the name of the piece that is being moved
  - the starting square of a piece that is clicked
  - the distance in the x-direction that a player is trying to move the piece
  - the distance in the y-direction that a player is trying to move the piece
  - the landing square of where a player is returning the piece to the board

  Having this information printed out to the standard output as we test and construct the
  solution allows us to understand the constructs of the game...This code snippet below
  should be pasted into the following place:

    public void mouseReleased(MouseEvent e) {

    ...
    ...
    ...
    Boolean validMove = false;

    */
        int landingX = (e.getX() / 75);
        int landingY = (e.getY() / 75);
        int xMovement = Math.abs((e.getX() / 75) - startX);
        int yMovement = Math.abs((e.getY() / 75) - startY);
        System.out.println("----------------------------------------------");
        System.out.println("The piece that is being moved is : " + pieceName);
        System.out.println("The starting coordinates are : " + "( " + startX + "," + startY + ")");
        System.out.println("The xMovement is : " + xMovement);
        System.out.println("The yMovement is : " + yMovement);
        System.out.println("The landing coordinates are : " + "( " + landingX + "," + landingY + ")");
        System.out.println("----------------------------------------------");


        Boolean validMove = false;

		/*
            The only piece that has been enabled to move is a White Pawn...but we should really have this is a separate
			method somewhere...how would this work.
			
			So a Pawn is able to move two squares forward one its first go but only one square after that. 
			The Pawn is the only piece that cannot move backwards in chess...so be careful when committing 
			a pawn forward. A Pawn is able to take any of the opponent’s pieces but they have to be one 
			square forward and one square over, i.e. in a diagonal direction from the Pawns original position. 
			If a Pawn makes it to the top of the other side, the Pawn can turn into any other piece, for 
			demonstration purposes the Pawn here turns into a Queen.
		*/

        if (pieceName.equals("BlackPawn")) {
            validMove = true;
        }
//-----------------------------------------------------(Queen)-----------------------------------------------------------------------
        if(pieceName.contains("Queen")) {

            Boolean inTheWay = false; // Boolean to test for pieces in the path of the Queen

            int distance = Math.abs(startX-landingX);

            if((landingX < 0) || (landingX > 7) || (landingY < 0) || (landingY > 7)) { // landing spot has to be on the actual board!
                validMove = false;
            }

            // Bishop-like moves for the queen...
            else {
               // validMove = true;
                if(xMovement==yMovement) {
                    if((startX-landingX < 0) && (startY-landingY < 0)) {
                        for(int i=0; i<distance; i++) {
                            if(piecePresent((initialX+(i*75)), (initialY+(i*75)))) {
                                inTheWay = true;
                            }
                        }
                    }
                    else if ((startX-landingX < 0) && (startY-landingY > 0)) {
                        for(int i=0; i<distance; i++) {
                            if(piecePresent((initialX+(i*75)), (initialY-(i*75)))) {
                                inTheWay = true;
                            }
                        }
                    }
                    else if ((startX-landingX > 0) && (startY-landingY > 0)) {
                        for(int i=0; i<distance; i++) {
                            if(piecePresent((initialX-(i*75)), (initialY-(i*75)))) {
                                inTheWay = true;
                            }
                        }
                    }
                    else if ((startX-landingX > 0) && (startY-landingY < 0)) {
                        for(int i=0; i<distance; i++) {
                            if(piecePresent((initialX-(i*75)), (initialY+(i*75)))) {
                                inTheWay = true;
                            }
                        }
                    }

                    if(inTheWay) {
                        validMove = false;
                    }
                    else {
                        if(piecePresent(e.getX(), (e.getY()))) {
                            if(pieceName.contains("White")) {
                                if(checkWhiteOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                }
                                else {
                                    validMove = false;
                                }
                            }
                            else {
                                if(checkBlackOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                }
                                else {
                                    validMove = false;
                                }
                            }
                        }
                        else {
                            validMove = true;
                        }
                    }
                }

                // Rook-like moves for the Queen...
                else if (((xMovement!=0)&&(yMovement == 0))|| ((xMovement==0)&&(yMovement!=0)))
                {
                    if(xMovement!=0) {
                        if(startX-landingX > 0) {
                            for(int i=0; i < xMovement; i++) {
                                if(piecePresent(initialX-(i*75), e.getY())) {
                                    inTheWay = true;
                                    break;
                                }
                                else {
                                    inTheWay = false;
                                }
                            }
                        }
                        else {
                            for(int i=0; i < xMovement; i++) {
                                if(piecePresent(initialX+(i*75), e.getY())) {
                                    inTheWay = true;
                                    break;
                                }
                                else {
                                    inTheWay = false;
                                }
                            }
                        }
                    }
                    else {
						/* int removed as already defined in global scope */ yMovement = Math.abs(startY-landingY);
                        if(startY-landingY > 0) {
                            for(int i = 0; i < yMovement; i++) {
                                if(piecePresent(e.getX(), initialY-(i*75))) {
                                    inTheWay = true;
                                    break;
                                }
                                else {
                                    inTheWay = false;
                                }
                            }
                        }
                        else {
                            for(int i = 0; i < yMovement; i++) {
                                if(piecePresent(e.getX(), initialY+(i*75))) {
                                    inTheWay = true;
                                    break;
                                }
                                else {
                                    inTheWay = false;
                                }
                            }
                        }
                    }

                    if(inTheWay) {
                        validMove = false;
                    }
                    else {
                        if(piecePresent(e.getX(), (e.getY()))) {
                            if(pieceName.contains("White")) {
                                if(checkWhiteOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                }
                                else {
                                    validMove = false;
                                }
                            }
                            else {
                                if(checkBlackOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                }
                                else {
                                    validMove = false;
                                }
                            }
                        }
                        else {
                            validMove = true;
                        }
                    }
                }

                else {
                    validMove = false;
                }
            }
        }

//-------------------------------------------------(Bishop)--------------------------------------------------------------
        if(pieceName.contains("Bishup")) {
            //validMove = true;
            Boolean inTheWay = false;
            int distance = Math.abs(startX-landingX);

            //if the piece is not on the board it cant move
            if((landingX < 0) || (landingX > 7) || (landingY < 0) || (landingY > 7)) {
                validMove = false;
            }
            else {
              // if X and Y movement are the same then it is diagonal.
                if(xMovement==yMovement) {
                    if((startX-landingX < 0) && (startY-landingY < 0)) {
                        for(int i=0; i<distance; i++) {
                            if(piecePresent((initialX+(i*75)), (initialY+(i*75)))) {
                                inTheWay = true;
                            }
                        }
                    }
                    else if ((startX-landingX < 0) && (startY-landingY > 0)) {
                        for(int i=0; i<distance; i++) {
                            if(piecePresent((initialX+(i*75)), (initialY-(i*75)))) {
                                inTheWay = true;
                            }
                        }
                    }
                    else if ((startX-landingX > 0) && (startY-landingY > 0)) {
                        for(int i=0; i<distance; i++) {
                            if(piecePresent((initialX-(i*75)), (initialY-(i*75)))) {
                                inTheWay = true;
                            }
                        }
                    }
                    else if ((startX-landingX > 0) && (startY-landingY < 0)) {
                        for(int i=0; i<distance; i++) {
                            if(piecePresent((initialX-(i*75)), (initialY+(i*75)))) {
                                inTheWay = true;
                            }
                        }
                    }

                    if(inTheWay) {
                        validMove = false;
                    }
                    else {
                        if(piecePresent(e.getX(), (e.getY()))) {
                            if(pieceName.contains("White")) {
                                if(checkWhiteOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                }
                                else {
                                    validMove = false;
                                }
                            }
                            else {
                                if(checkBlackOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                }
                                else {
                                    validMove = false;
                                }
                            }
                        }
                        else {
                            validMove = true;
                        }
                    }
                }
                else {
                    validMove = false;
                }
            }
        }

        //------------------------------------------------------(Rook)--------------------------------------------------------------------------------------------


        else if (pieceName.contains("Rook")) {

            Boolean inTheWay = false;

            if ((landingX < 0) || (landingX > 7) || (landingY < 0) || (landingY > 7)) { // if the Rook ain't on the board - no dice!
                validMove = false;
            }

            // The rook can only move along the X-axis OR the Y-axis, not both at the same time.
            else {
                if (((xMovement != 0) && (yMovement == 0)) || ((xMovement == 0) && (yMovement != 0))) {
                    if (xMovement != 0) {

                        if (xMovement > 0) {
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent(initialX - (i * 75), e.getY())) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        } else {
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent(initialX + (i * 75), e.getY())) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        }
                    } else {

                        if (yMovement > 0) {
                            for (int i = 0; i < yMovement; i++) {
                                if (piecePresent(e.getX(), initialY - (i * 75))) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        } else {
                            for (int i = 0; i < yMovement; i++) {
                                if (piecePresent(e.getX(), initialY + (i * 75))) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        }
                    }

                    if (inTheWay) {
                        validMove = false;
                    } else {
                        if (piecePresent(e.getX(), (e.getY()))) {
                            if (pieceName.contains("White")) {
                                if (checkWhiteOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            } else {
                                if (checkBlackOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            }
                        } else {
                            validMove = true;
                        }
                    }
                } else {
                    validMove = false;
                }
            }
        }




        System.out.println("startX is:" + startX);
        System.out.println("startY is:" + startY);
        System.out.println("x movement is:" + xMovement);
        System.out.println("y movement is:" + yMovement);
        System.out.println("landingX is:" + landingX);
        System.out.println("landingY is:" + landingY);
        System.out.println("landingY is:" + landingY);
//------------------------------------------------------(BlackKnight)--------------------------------------------------------------------------------------------

        if (pieceName.equals("BlackKnight")) {

            //If the Pawn is still in the boundary of the board
            if (((startX >= 0) || (startX <= 7))) {
                //If there is a piece on the Square AND its a diagonal move  Fix 2:  added condition that newY was greater than startY to ensure it cant take a piece going backwards.
                if (((xMovement == 1) && (yMovement == 2)) || (xMovement == 2) && (yMovement == 1)) {
                    //Check if the piece is Black
                    if ((piecePresent(e.getX(), (e.getY())))) {
                        if (checkBlackOponent(e.getX(), e.getY())) {
                            validMove = true;
                        } else {
                            validMove = false;
                        }
                    } else if ((!piecePresent(e.getX(), (e.getY())))) {
                        validMove = true;
                    }
                }
            }
        }


//-------------------------------------------------(WhiteKnight)--------------------------------------------------------------------------------------------

        else if (pieceName.equals("WhiteKnight")) {

            //If the Pawn is still in the boundary of the board
            if (((startX >= 0) || (startX <= 7))) {
                //If there is a piece on the Square AND its a diagonal move  Fix 2:  added condition that newY was greater than startY to ensure it cant take a piece going backwards.
                if (((xMovement == 1) && (yMovement == 2)) || (xMovement == 2) && (yMovement == 1)) {
                    //Check if the piece is Black
                    if ((piecePresent(e.getX(), (e.getY())))) {
                        if (checkWhiteOponent(e.getX(), e.getY())) {
                            validMove = true;
                        } else {
                            validMove = false;
                        }
                    } else if ((!piecePresent(e.getX(), (e.getY())))) {
                        validMove = true;
                    }
                }
            }
        }

//------------------------------------------------------------------------------------------------------------------------

        else if (pieceName.equals("Rook")) {

            validMove = true;
        } else if (pieceName.equals("Bishop")) {

            validMove = true;
        } else if (pieceName.equals("King")) {

            validMove = true;
        } else if (pieceName.equals("Queen")) {

            validMove = true;
        }

//-------------------------------------------------(WhitePawn)--------------------------------------------------------------------------------------------
        if (pieceName.equals("WhitePawn")) {
            //If it is the pawns first move
            if (startY == 1) {
                //If the pawn moves 1 Square OR 2 Squares
                if ((startX == (e.getX() / 75)) && ((((e.getY() / 75) - startY) == 1) || ((e.getY() / 75) - startY) == 2)) {
                    //So if the pawn moves 2 Squares
                    if ((((e.getY() / 75) - startY) == 2)) {
                        //If there is no piece on the landing square AND no piece on the Square before it
                        /* This is Fix 1 to check for the square before the landing square instead of after it, +75 became -75*/
                        if ((!piecePresent(e.getX(), (e.getY())) && (!piecePresent(e.getX(), e.getY() - 75)))) {
                            validMove = true;
                        } else {
                            validMove = false;
                        }

                        // So if the pawn only moves 1 Square
                    } else

                    {
                        //If there is no piece present it is valid
                        if ((!piecePresent(e.getX(), (e.getY())))) {
                            validMove = true;

                        } else {
                            // If there is a piece there then it can't move and its not valid
                            validMove = false;
                        }

                    }


                } else {
                    //It cant move any further than the 2 squares or sideways
                    validMove = false;
                }

                // For any subsequent move of the pawn after its first move
            } else {
                //Get the new co-ordinates
                int newY = e.getY() / 75;
                int newX = e.getX() / 75;

                //If the Pawn is still in the boundary of the board
                if (((startX - 1 >= 0) || (startX + 1 <= 7))) {
                    //If there is a piece on the Square AND its a diagonal move  Fix 2:  added condition that newY was greater than startY to ensure it cant take a piece going backwards.
                    if ((piecePresent(e.getX(), (e.getY()))) && ((((newX == (startX + 1) && (startX + 1 <= 7) && (newY == startY + 1))) || ((newX == (startX - 1)) && (startX - 1 >= 0) && (newY == startY + 1))))) {
                        //Check if the piece is Black
                        if (checkWhiteOponent(e.getX(), e.getY())) {
                            validMove = true;

                            //If your starting point was 1 Square away from the last row
                            if (startY == 6) {
                                //This means you successfully took a piece that can change to a White Queen
                                success = true;
                            }
                        } else {
                            validMove = false;
                        }

                    } else {

                        //If there is no piece present
                        if (!piecePresent(e.getX(), (e.getY()))) {
                            //If you moved 1 Square forward
                            if ((startX == (e.getX() / 75)) && ((e.getY() / 75) - startY) == 1) {
                                //If your starting point was 1 Square away from the last row
                                if (startY == 6) {
                                    //This means you can change to a White Queen
                                    success = true;
                                }
                                validMove = true;
                            } else {
                                validMove = false;
                            }
                        } else {
                            validMove = false;
                        }
                    }
                } else {
                    validMove = false;
                }
            }
        }


//--------------------------------------------------------------------------------------------------------------------------------
        if (!validMove)

        {
            int location = 0;
            if (startY == 0) {
                location = startX;
            } else {
                location = (startY * 8) + startX;
            }


            String pieceLocation = pieceName + ".png";
            pieces = new JLabel(new ImageIcon(pieceLocation));
            panels = (JPanel) chessBoard.getComponent(location);
            panels.add(pieces);
        } else

        {
            if (success) {
                int location = 56 + (e.getX() / 75);
                if (c instanceof JLabel) {
                    Container parent = c.getParent();
                    parent.remove(0);
                    pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                    parent = (JPanel) chessBoard.getComponent(location);
                    parent.add(pieces);
                } else {
                    Container parent = (Container) c;
                    pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                    parent = (JPanel) chessBoard.getComponent(location);
                    parent.add(pieces);
                }
            } else {
                if (c instanceof JLabel) {
                    Container parent = c.getParent();
                    parent.remove(0);
                    parent.add(chessPiece);
                } else {
                    Container parent = (Container) c;
                    parent.add(chessPiece);
                }
                chessPiece.setVisible(true);
            }
        }

    }


    public void mouseClicked(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    /*
        Main method that gets the ball moving.
    */
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


