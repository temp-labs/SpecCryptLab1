package ipt.lab.crypt.lab1.difftable.probsource;

import ipt.lab.crypt.common.Constants;
import ipt.lab.crypt.common.utils.SerializationUtil;

import java.io.IOException;
import java.nio.file.Path;

public class FileDiffPropTableSource implements DiffProbTableSource {

    public static final Path probsDir = Constants.BASE_DIR.resolve("prob_tables");

    @Override
    public long[][] getDiffProbTable(int sBoxNumber) {
        try {
            return SerializationUtil.deserialize(resolveFile(sBoxNumber), long[][].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTable(long[][] newTable, int sBoxNumber) {
        try {
            SerializationUtil.serialize(resolveFile(sBoxNumber), newTable);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Path resolveFile(int sBoxNumber) {
        return probsDir.resolve(sBoxNumber + ".bin");
    }
}
