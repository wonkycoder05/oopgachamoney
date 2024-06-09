package com.money;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


//jframe builder
public class Layout extends JFrame {
    private JButton logButton;
    private JTextField logger;
    private JComboBox<Games> comboBox1;
    private JSplitPane panel;
    private JTable table1;
    private JTextField charname;
    private JTextField pityin;
    private JPanel form;
    private JTextField DateField;
    private JTextField month;
    private JTextField year;
    private JButton exportbtn;
    private JTable table2;
    private JScrollPane scroll2;
    private JTabbedPane tabs;
    private JButton importbtn;
    //sql connectors





    public Layout() {

        // make a new game list in le combobox
        List<Games> gameList = new ArrayList<>();
        gameList.add(new Games("Wuthering Waves", 900608720));
        gameList.add(new Games("Genshin Impact", 828527791));
        gameList.add(new Games("Honkai Star Rail", 829911471));
        //combo box for the list above
        DefaultComboBoxModel<Games> gamesDefaultComboBoxModel = new DefaultComboBoxModel<>(gameList.toArray(new Games[0]));
        comboBox1.setModel(gamesDefaultComboBoxModel);
        //adding a default game to test
        List<GameLogs> gameLogs = new ArrayList<>();



        // Add the scroll pane to the layout
        setContentPane(panel);
        //set a new table every time the log is pressed
        DataTable dataTable = new DataTable(gameLogs);
        table1.setModel(dataTable);
        table1.setAutoCreateRowSorter(true);
        // Initialize table2 and its scroll pane


        logButton.addActionListener(e -> {
            //listener stuff to take in the input. set new table everytime log is pressed
            GameLogs logs = new GameLogs();
            logs.setPity(Integer.parseInt(pityin.getText()));
            logs.setGamesName(String.valueOf((comboBox1.getSelectedItem())));
            logs.setCharName(charname.getText());
            logs.setSpending(Integer.parseInt((logger.getText())));
            logs.setDay(Integer.parseInt(DateField.getText()));
            logs.setMonth(Integer.parseInt(month.getText()));
            logs.setYear(Integer.parseInt(year.getText()));


            gameLogs.add(logs);
            dataTable.fireTableDataChanged();
        });

        exportbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = "C:\\Users\\Magda\\Desktop\\pullsdata\\pulldata.txt";

                File file = new File(filePath);
                try {
                    FileWriter fw = new FileWriter(file, true);
                    BufferedWriter bw = new BufferedWriter(fw);

                    for (int i = 0; i < table1.getRowCount(); i++) {//rows
                        for (int j = 0; j < table1.getColumnCount(); j++) {//columns
                            bw.write(table1.getValueAt(i, j).toString() + "/");
                        }
                        bw.newLine();
                    }

                    bw.close();
                    fw.close();

                } catch (IOException ex) {
                    Logger.getLogger(Layout.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });


        importbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = "C:\\Users\\Magda\\Desktop\\pullsdata\\pulldata.txt";

                File file = new File(filePath);
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    // get the first line
                    // get the columns name from the first line
                    // set columns name to the jtable model
                    String firstLine = br.readLine().trim();
                    String[] columnsName = firstLine.split(" / ");
                    DefaultTableModel model = (DefaultTableModel)table2.getModel();
                    model.setColumnIdentifiers(columnsName);

                    // get lines from txt file
                    Object[] tableLines = br.lines().toArray();

                    // extratct data from lines
                    // set data to jtable model
                    for(int i = 0; i < tableLines.length; i++)
                    {
                        String line = tableLines[i].toString().trim();
                        String[] dataRow = line.split(" / ");
                        model.addRow(dataRow);
                    }


                } catch (Exception ex) {
                    Logger.getLogger(Layout.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }
    //jframe visuals
    public static void main(String[] args) {
        Layout l = new Layout();
        l.setTitle("Financial Logger for Gacha Addicts");
        l.setSize(1200, 600);
        l.setContentPane(new Layout().panel);
        l.setVisible(true);
        l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    //make the table using abstracttablemodel


    private static class DataTable extends AbstractTableModel{

        //make table titles
        private final String[] COLUMNS = {"GAME", "SPENDING", "PITY", "FIVE STAR", "DATE"};
        private final List<GameLogs> gameLogs;

        public DataTable(List<GameLogs> gameLogs) {
            this.gameLogs = gameLogs;
        }

        //overriding the superclass
        @Override
        public int getRowCount() {
            return gameLogs.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            //putting in the dates in a string
            String dateString = String.format("%02d-%02d-%04d", gameLogs.get(rowIndex).getDay(), gameLogs.get(rowIndex).getMonth(), gameLogs.get(rowIndex).getYear());
            return switch (columnIndex) {
                //error handling
                default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
                case 0 -> gameLogs.get(rowIndex).getGamesName();
                case 1 -> gameLogs.get(rowIndex).getSpending();
                case 2 -> gameLogs.get(rowIndex).getPity();
                case 3 -> gameLogs.get(rowIndex).getCharName();
                case 4 -> dateString;

            };
        }



        @Override
        //makes the columns
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (getValueAt(0, columnIndex) !=null){
                return (getValueAt((0), columnIndex).getClass());
            } else {
                return Object.class;
            }
        }
    }

}
