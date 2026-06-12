import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class Persistencia {
    public void salvar(Jogo jogo, String arquivo) throws Exception { //Temporariamente lançando exceção até implementar try catch
        FileOutputStream fos = new FileOutputStream(arquivo);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(jogo);

        oos.close();
        fos.close();
    }

    public Jogo carregar(String arquivo) throws Exception { //Temporariamente lançando exceção até implementar try catch
        FileOutputStream fos = new FileOutputStream(arquivo);
        FileInputStream fis = new FileInputStream(arquivo);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Jogo jogo = (Jogo) ois.readObject();

        ois.close();
        fis.close();

        return jogo;
    }
}