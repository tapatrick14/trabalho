package controle;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * This class demonstrates how to load an Image from an external file
 */
public class CarregarImagem extends Component {
          
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage img;
	int matriz[][] = null;
	
	Filtros filtro = new Filtros();
	

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public CarregarImagem() {
       try {
           img = ImageIO.read(new File("japao3.png"));
           img = filtro.tomCinza(img);
           ImageIO.write(img, "PNG", new File("1.png")); // salvar imagem
           filtro.imprimePixel(filtro.matrizTransposta(filtro.acharPixel(img)));
           matriz = filtro.matrizTransposta(filtro.acharPixel(img));
           filtro.formulaAlturaY(matriz);
           
           
       } catch (IOException e) {
       }

    }

	public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }

    public static void main(String[] args) {

        JFrame f = new JFrame("Imagem");
            
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

        f.add(new CarregarImagem());
        f.pack();
        f.setVisible(true);
    }
}