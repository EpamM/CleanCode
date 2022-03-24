package com.epam.engx.cleancode.complextask.task1;


import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.Command;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DataSet;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.View;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DatabaseManager;

import java.util.List;


public class Print implements Command {

    private View view;
    private DatabaseManager manager;
    private String tableName;

    public Print(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    public boolean canProcess(String command) {
        return command.startsWith("print ");
    }

    public void process(String input) {
        String[] command = input.split(" ");
        if (command.length != 2) {
            throw new IllegalArgumentException("incorrect number of parameters. Expected 1, but is " + (command.length - 1));
        }
        tableName = command[1];
        List<DataSet> data = manager.getTableData(tableName);
        view.write(getTableString(data));
    }

    private String getTableString(List<DataSet> data) {
        int maxColumnSize;
        maxColumnSize = getMaxColumnSize(data);
        if (maxColumnSize == 0) {
            return getEmptyTable(tableName);
        } else {
            return getHeaderOfTheTable(data) + getStringTableData(data);
        }
    }

    private String getEmptyTable(String tableName) {
        String textEmptyTable = "║ Table '" + tableName + "' is empty or does not exist ║";
        StringBuilder resultBld = new StringBuilder("╔");
        for (int i = 0; i < textEmptyTable.length() - 2; ++i) {
            resultBld.append("═");
        }
        resultBld.append("╗\n");
        resultBld.append(textEmptyTable + "\n");
        resultBld.append("╚");
        getMaxColumnData(resultBld, textEmptyTable.length() - 2, "═");
        resultBld.append("╝\n");
        return resultBld.toString();
    }

    private int getMaxColumnSize(List<DataSet> dataSets) {
        int maxLength = 0;
        if (dataSets.isEmpty()) {
            return maxLength;
        }
        List<String> columnNames = dataSets.get(0).getColumnNames();
        for (String columnName : columnNames) {
            if (columnName.length() > maxLength) {
                maxLength = columnName.length();
            }
        }
        for (DataSet dataSet : dataSets) {
            List<Object> values = dataSet.getValues();
            for (Object value : values) {
                if (String.valueOf(value).length() > maxLength) {
                    maxLength = String.valueOf(value).length();
                }
            }
        }
        return maxLength;
    }

    private String getStringTableData(List<DataSet> dataSets) {
        int rowsCount;
        rowsCount = dataSets.size();
        StringBuilder resultBld = new StringBuilder("");
        int maxColumnSize = getMaxColumnSize(dataSets);

        if (maxColumnSize % 2 == 0) {
            maxColumnSize += 2;
        } else {
            maxColumnSize += 3;
        }
        int columnCount = getColumnCount(dataSets);
        getRowData(dataSets, rowsCount, resultBld, maxColumnSize, columnCount);
        resultBld.append("╚");
        getColumnData(resultBld, maxColumnSize, columnCount, "╩");
        getMaxColumnData(resultBld, maxColumnSize, "═");
        resultBld.append("╝\n");
        return resultBld.toString();
    }

    private void getColumnData(StringBuilder resultBld, int maxColumnSize, int columnCount, String s) {
        for (int j = 1; j < columnCount; j++) {
            getMaxColumnData(resultBld, maxColumnSize, "═");
            resultBld.append(s);
        }
    }

    private void getMaxColumnData(StringBuilder resultBld, int maxColumnSize, String s) {
        for (int i = 0; i < maxColumnSize; i++) {
            resultBld.append(s);
        }
    }

    private void getRowData(List<DataSet> dataSets, int rowsCount, StringBuilder resultBld, int maxColumnSize, int columnCount) {
        for (int row = 0; row < rowsCount; row++) {
            List<Object> values = dataSets.get(row).getValues();
            resultBld.append("║");
            for (int column = 0; column < columnCount; column++) {
                int valuesLength = String.valueOf(values.get(column)).length();
                if (valuesLength % 2 == 0) {
                    getMaxColumnData(resultBld, (maxColumnSize - valuesLength) / 2, " ");
                    resultBld.append(String.valueOf(values.get(column)));
                    getMaxColumnData(resultBld, (maxColumnSize - valuesLength) / 2, " ");
                    resultBld.append("║");
                } else {
                    getMaxColumnData(resultBld, (maxColumnSize - valuesLength) / 2, " ");
                    resultBld.append(String.valueOf(values.get(column)));
                    for (int j = 0; j <= (maxColumnSize - valuesLength) / 2; j++) {
                        resultBld.append(" ");
                    }
                    resultBld.append("║");
                }
            }
            resultBld.append("\n");
            if (row < rowsCount - 1) {
                resultBld.append("╠");
                getColumnData(resultBld, maxColumnSize, columnCount, "╬");
                getMaxColumnData(resultBld, maxColumnSize, "═");
                resultBld.append("╣\n");
            }
        }
    }

    private int getColumnCount(List<DataSet> dataSets) {
        int result = 0;
        if (dataSets.isEmpty()) {
            return result;
        }
        return dataSets.get(0).getColumnNames().size();
    }

    private String getHeaderOfTheTable(List<DataSet> dataSets) {
        int maxColumnSize = getMaxColumnSize(dataSets);
        StringBuilder resultBld = new StringBuilder("");
        int columnCount = getColumnCount(dataSets);
        if (maxColumnSize % 2 == 0) {
            maxColumnSize += 2;
        } else {
            maxColumnSize += 3;
        }
        resultBld.append("╔");
        getColumnData(resultBld, maxColumnSize, columnCount, "╦");
        getMaxColumnData(resultBld, maxColumnSize, "═");
        resultBld.append("╗\n");
        List<String> columnNames = dataSets.get(0).getColumnNames();
        for (int column = 0; column < columnCount; column++) {
            resultBld.append("║");
            int columnNamesLength = columnNames.get(column).length();
            if (columnNamesLength % 2 == 0) {
                getMaxColumnData(resultBld, (maxColumnSize - columnNamesLength) / 2, " ");
                resultBld.append(columnNames.get(column));
                getMaxColumnData(resultBld, (maxColumnSize - columnNamesLength) / 2, " ");
            } else {
                getMaxColumnData(resultBld, (maxColumnSize - columnNamesLength) / 2, " ");
                resultBld.append(columnNames.get(column));
                for (int j = 0; j <= (maxColumnSize - columnNamesLength) / 2; j++) {
                    resultBld.append(" ");
                }
            }
        }
        resultBld.append("║\n");

        //last string of the header
        if (dataSets.isEmpty()) {
            resultBld.append("╚");
            getColumnData(resultBld, maxColumnSize, columnCount, "╩");
            getMaxColumnData(resultBld, maxColumnSize, "═");
            resultBld.append("╝\n");
        } else {
            resultBld.append("╠");
            getColumnData(resultBld, maxColumnSize, columnCount, "╬");
            getMaxColumnData(resultBld, maxColumnSize, "═");
            resultBld.append("╣\n");
        }
        return resultBld.toString();
    }
}