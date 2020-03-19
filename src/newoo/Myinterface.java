/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newoo;

import java.util.Properties;

/**
 *
 * @author peegh
 */
public interface Myinterface {

    public String insert(String tableName, Properties value);

    public String update(String tableName, Properties value, String ID);

    public String delete(String tableName, String ID, String idTable);

    public String testupdate(String tableName, Properties value, String culumId);
}
