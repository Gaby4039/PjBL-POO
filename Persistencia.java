import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Persistencia {
    public static void salvar(Jogo jogo, String arquivo) throws IOException {
        Path destino = Paths.get(arquivo);
        Path parent = destino.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        Path temp = Paths.get(arquivo + ".tmp");

        try (FileOutputStream fos = new FileOutputStream(temp.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(jogo);
            oos.flush();
        }

        // mover arquivo temporário para destino (substitui se existir)
        try {
            Files.move(temp, destino, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (Exception e) {
            // fallback se ATOMIC_MOVE não for suportado
            Files.move(temp, destino, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static Jogo carregar(String arquivo) throws IOException, ClassNotFoundException {
        Path destino = Paths.get(arquivo);
        if (!Files.exists(destino)) {
            throw new FileNotFoundException("Arquivo não encontrado: " + arquivo);
        }

        try (FileInputStream fis = new FileInputStream(destino.toFile());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Jogo) ois.readObject();
        }
    }
}