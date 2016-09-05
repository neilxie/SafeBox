package com.max;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoAutoGenerator {
    public static void main(String[] args) throws Exception {
        int version = 1;
        String defaultJavaPackage = "com.max.security.greendao";
        // 正如你所见的，你创建了一个用于添加实体（Entity）的模式（Schema）对象。
        // 两个参数分别代表：数据库版本号与自动生成代码的包路径。
        Schema schema = new Schema(version, defaultJavaPackage);
//      当然，如果你愿意，你也可以分别指定生成的 Bean 与 DAO 类所在的目录，只要如下所示：
//      Schema schema = new Schema(1, "me.itangqi.bean");
//      schema.setDefaultJavaPackageDao("me.itangqi.dao");

        // 模式（Schema）同时也拥有两个默认的 flags，分别用来标示 entity 是否是 activie 以及是否使用 keep sections。
        // schema2.enableActiveEntitiesByDefault();
        // schema2.enableKeepSectionsByDefault();

        // 一旦你拥有了一个 Schema 对象后，你便可以使用它添加实体（Entities）了。
        createTable(schema);

        // 最后我们将使用 DAOGenerator 类的 generateAll() 方法自动生成代码，此处你需要根据自己的情况更改输出目录（既之前创建的 java-gen)。
        // 其实，输出目录的路径可以在 build.gradle 中设置，有兴趣的朋友可以自行搜索，这里就不再详解。
        // /表示根目录， ./表示当前路径， ../表示上一级父目录
        new DaoGenerator().generateAll(schema, "./app/src/main/java-gen");
    }

    private static void createTable(Schema schema) {

        Entity entity = schema.addEntity("FileModel");

        entity.addStringProperty("Id").notNull().primaryKey();
        entity.addStringProperty("FileName").notNull();
        entity.addStringProperty("FilePath").notNull();
        entity.addStringProperty("OriginPath").notNull();
        entity.addIntProperty("FileType").notNull();
        entity.addLongProperty("CreateTime").notNull();
    }
}
