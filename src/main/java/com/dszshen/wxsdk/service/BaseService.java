package com.dszshen.wxsdk.service;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangbin on 2016/7/28.
 */
@Service
public abstract class BaseService<T, ID extends Serializable> {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(BaseService.class);

    @Resource
    public DataSource mySqlDataSource;

    public DataSource getDataSource() {
        return this.mySqlDataSource;
    }

    protected QueryRunner getQueryRunner() {
        return new QueryRunner(mySqlDataSource);
    }

    /**
     * 组装sql，并执行
     * @param opType 操作数据库方式（insert ，update）
     * @param tableName 数据库表的名称
     * @param fields 表字段
     * @param params 参数
     * @return
     */
    /*public Integer createInsertSql(String opType, String tableName, List fields, JSONObject params){
        String sql = "";
        String opStr = "(";
        String valuesStr = " values (";
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(0, UUID.randomUUID().toString());
        for (int i=0;i<fields.size();i++){
            opStr+=fields.get(i).toString();
            valuesStr+="?";
            if((i+1)==fields.size()){
                opStr+=")";
                valuesStr+=")";
            }else{
                arrayList.add(i+1,params.getString(fields.get(i+1).toString()));
                opStr+=",";
                valuesStr+=",";
            }
        }
        sql = "INSERT INTO "+tableName+opStr+valuesStr;
        Object[] opParams = arrayList.toArray();
        return insert(sql,opParams);
    }*/

    /**
     *
     * @param opType
     * @param tableName
     * @param fields
     * @param params
     * @param updateFields
     * @return
     */
    /*public Integer createUpdateSql(String opType, String tableName, List fields, JSONObject params,List<String> updateFields){
        String sql = "";
        String opStr = "";
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i=0;i<fields.size();i++){
            if((i+1)!=fields.size()){
                opStr+=fields.get(i+1).toString()+"=?,";
                arrayList.add(i,params.getString(fields.get(i+1).toString()));
            }
        }
        opStr=opStr.substring(0,opStr.length()-1);
        sql = "UPDATE "+tableName+" SET "+opStr+" WHERE ";
        String whereStr = " 1=1 AND";
        if(updateFields!=null&&updateFields.size()>0){
            for (int j=0;j<updateFields.size();j++){
                whereStr+= updateFields.get(j)+"="+params.getString(updateFields.get(j));
                if((j+1)!=updateFields.size()){
                    whereStr+=" AND ";
                }
            }
        }

        sql+= whereStr;
        Object[] opParams = arrayList.toArray();
        return update(sql,opParams);
    }*/

    /**
     *
     * @param sql 插入的sql语句
     * @param params 插入的参数
     * @return 返回影响行数
     */
    public int insert(String sql, Object[] params) {
        QueryRunner queryRunner = getQueryRunner();
        int affectedRows = 0;
        try {
            if (params == null) {
                affectedRows = queryRunner.update(sql);
            } else {
                affectedRows = queryRunner.update(sql, params);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    public int update(String sql) {
        return update(sql, null);
    }

    public int update(String sql, Object param) {
        return update(sql, new Object[] { param });
    }

    public int update(String sql, Object[] params) {
        QueryRunner queryRunner = getQueryRunner();
        int affectedRows = 0;
        try {
            if (params == null) {
                affectedRows = queryRunner.update(sql);
            } else {
                affectedRows = queryRunner.update(sql, params);
            }
        } catch (SQLException e) {
            LOGGER.error("Error occured while attempting to update data", e);
        }
        return affectedRows;
    }

    public int[] batchUpdate(String sql, Object[][] params) {
        QueryRunner queryRunner = getQueryRunner();
        int[] affectedRows = new int[0];
        try {
            affectedRows = queryRunner.batch(sql, params);
        } catch (SQLException e) {
            LOGGER.error("Error occured while attempting to batch update data",
                    e);
        }
        return affectedRows;
    }

    public List<Map<String, Object>> find(String sql) {
        return find(sql, null);
    }

    public List<Map<String, Object>> find(String sql, Object param) {
        return find(sql, new Object[] { param });
    }

    public List<Map<String, Object>> find(String sql, Object[] params) {
        QueryRunner queryRunner = getQueryRunner();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            if (params == null) {
                list = (List<Map<String, Object>>) queryRunner.query(sql,
                        new MapListHandler());
            } else {
                list = (List<Map<String, Object>>) queryRunner.query(sql,
                        new MapListHandler(), params);
            }
        } catch (SQLException e) {
            LOGGER.error("Error occured while attempting to query data", e);
        }
        return list;
    }

    public <T> List<T> find(Class<T> entityClass, String sql) {
        return find(entityClass, sql, null);
    }

    public <T> List<T> find(Class<T> entityClass, String sql, Object param) {
        return find(entityClass, sql, new Object[] { param });
    }

    public <T> List<T> find(Class<T> entityClass, String sql, Object[] params) {
        QueryRunner queryRunner = getQueryRunner();
        List<T> list = new ArrayList<T>();
        try {
            if (params == null) {
                list = (List<T>) queryRunner.query(sql, new BeanListHandler<T>(
                        entityClass));
            } else {
                list = (List<T>) queryRunner.query(sql, new BeanListHandler<T>(
                        entityClass), params);
            }
        } catch (SQLException e) {
            LOGGER.error("Error occured while attempting to query data", e);
        }
        return list;
    }

    public <T> T findFirst(Class<T> entityClass, String sql) {
        return findFirst(entityClass, sql, null);
    }

    public <T> T findFirst(Class<T> entityClass, String sql, Object param) {
        return findFirst(entityClass, sql, new Object[] { param });
    }

    public <T> T findFirst(Class<T> entityClass, String sql, Object[] params) {
        QueryRunner queryRunner = getQueryRunner();
        T object = null;
        try {
            if (params == null) {
                object = queryRunner
                        .query(sql, new BeanHandler<T>(entityClass));
            } else {
                object = queryRunner.query(sql,
                        new BeanHandler<T>(entityClass), params);
            }
        } catch (SQLException e) {
            LOGGER.error("Error occured while attempting to query data", e);
        }
        return object;
    }

    public Map<String, Object> findFirst(String sql) {
        return findFirst(sql, null);
    }

    public Map<String, Object> findFirst(String sql, Object param) {
        return findFirst(sql, new Object[] { param });
    }

    public Map<String, Object> findFirst(String sql, Object[] params) {
        QueryRunner queryRunner = getQueryRunner();
        Map<String, Object> map = null;
        try {
            if (params == null) {
                map = (Map<String, Object>) queryRunner.query(sql,
                        new MapHandler());
            } else {
                map = (Map<String, Object>) queryRunner.query(sql,
                        new MapHandler(), params);
            }
        } catch (SQLException e) {
            LOGGER.error("Error occured while attempting to query data", e);
        }
        return map;
    }

    public Object findBy(String sql, String columnName) {
        return findBy(sql, columnName, null);
    }

    public Object findBy(String sql, String columnName, Object param) {
        return findBy(sql, columnName, new Object[] { param });
    }

    public Object findBy(String sql, String columnName, Object[] params) {
        QueryRunner queryRunner = getQueryRunner();
        Object object = null;
        try {
            if (params == null) {
                object = queryRunner.query(sql, new ScalarHandler<Object>(
                        columnName));
            } else {
                object = queryRunner.query(sql, new ScalarHandler<Object>(
                        columnName), params);
            }
        } catch (SQLException e) {
            LOGGER.error("Error occured while attempting to query data", e);
        }
        return object;
    }

    public Object findBy(String sql, int columnIndex) {
        return findBy(sql, columnIndex, null);
    }

    public Object findBy(String sql, int columnIndex, Object param) {
        return findBy(sql, columnIndex, new Object[] { param });
    }

    public Object findBy(String sql, int columnIndex, Object[] params) {
        QueryRunner queryRunner = getQueryRunner();
        Object object = null;
        try {
            if (params == null) {
                object = queryRunner.query(sql, new ScalarHandler<Object>(
                        columnIndex));
            } else {
                object = queryRunner.query(sql, new ScalarHandler<Object>(
                        columnIndex), params);
            }
        } catch (SQLException e) {
            LOGGER.error("Error occured while attempting to query data", e);
        }
        return object;
    }

}
