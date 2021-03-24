package org.szh.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author Terry
 * @DATE 2021/1/11 10:32
 * @Version 1.0
 **/
public class CodeGenerator {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATA_URL = "jdbc:mysql://localhost:3306/db_test?characterEncoding=UTF-8&useSSL=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String ROOT_DIR = "D:/workFile/workspace/mybatis-plus-generate-code/";
    private static final String AUTHOR = "Terry";

    public static InjectionConfig getXMLConfig() {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
        //XML  generation path
        List<FileOutConfig> focList = new ArrayList<>();
//        String defaultPath = "/templates/mapper.xml.vm";
        String freemarkerPath = "/templates/mapper.xml.ftl";
        focList.add(new FileOutConfig(freemarkerPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return ROOT_DIR + "src/main/resources/mybatis-mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    public static PackageConfig getPackageConfig() {
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.szh");
//        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setEntity("bean.po");
        pc.setMapper("dao");
        return pc;
    }

    public static StrategyConfig getStrategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        // your table prefix
//        strategy.setTablePrefix(new String[]{"t_"});
        //Naming strategy for mapping database tables to entities: underscore to camel case naming
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //Naming strategy for mapping database table fields to entities: underscore to camel case naming
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude(new String[]{"t_contact_info"}); // 需要生成的表
        // [Entity] Is it a lombok model (default false)
        strategy.setEntityLombokModel(true);
        //Camel case to hyphen, receiving parameter in  controller
//        strategy.setControllerMappingHyphenStyle(true);
        return strategy;
    }

    public static GlobalConfig getGlobalConfig() {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(ROOT_DIR + "/src/main/java");
        // 覆盖文件 false，不覆盖
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        // XML Secondary cache
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columnList
        gc.setBaseColumnList(true);
        gc.setAuthor(AUTHOR);
        gc.setEntityName("%sPO");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        return gc;
    }

    public static DataSourceConfig getDataSource() {
        // 配置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName(DRIVER);
        dsc.setUrl(DATA_URL);
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);
        return dsc;
    }

    public static TemplateConfig getTemplateConfig() {
        TemplateConfig tc = new TemplateConfig();
        tc.setController("");
        // exist,first comment remove
//        tc.setService("");
//        tc.setServiceImpl("");
        tc.setXml(null);
        return tc;
    }

    public static void main(String[] args) {
        DataSourceConfig dsc = getDataSource();
        // Global configuration
        GlobalConfig gc = getGlobalConfig();
        // Generation strategy
        StrategyConfig strategy = getStrategyConfig();
        // Package where the configuration file is located
        PackageConfig pc = getPackageConfig();
        //xml configuration
        InjectionConfig cfg = getXMLConfig();
        // Turn off the default xml generation, adjust the generation to the root directory
        TemplateConfig tc = getTemplateConfig();
        AutoGenerator mpg = new AutoGenerator();
        mpg.setDataSource(dsc);
        mpg.setGlobalConfig(gc);
        mpg.setStrategy(strategy);
        mpg.setPackageInfo(pc);
        mpg.setCfg(cfg);
        mpg.setTemplate(tc);
        FreemarkerTemplateEngine engine = new FreemarkerTemplateEngine();
        mpg.setTemplateEngine(engine);
        // do it
        mpg.execute();
    }
}
