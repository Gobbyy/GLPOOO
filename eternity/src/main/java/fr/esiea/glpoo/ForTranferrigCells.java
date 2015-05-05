package fr.esiea.glpoo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ForTranferrigCells {

    public static void main(String[] args) {
          DefaultTableModel tableModel=new DefaultTableModel(
            new Object [][] {
                {"a", null, null, null,null,null,null},
                {null, null, "b", null,null,null,null},
                {null, null, null, null,null,null,null},
                {null, null, null, null,null,null,null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4","Title 5","Title 6","Title 7"
            }
        );
        JTable table = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }

            private boolean pressed;

                 @Override
                 protected void processMouseEvent(MouseEvent e) {
                         pressed = e.getID() == MouseEvent.MOUSE_PRESSED;
                         if (pressed && !e.isShiftDown() && !e.isControlDown())
                                 clearSelection();
                         try {
                                 super.processMouseEvent(e);
                         } finally {
                                 pressed = false;
                         }
                 }

                 @Override
                 public boolean isCellSelected(int row, int col) {
                         return pressed ? true : super.isCellSelected(row, col);
                 }



        };

        table.setModel(tableModel);

        table.setDragEnabled(true);
        table.setDropMode(DropMode.USE_SELECTION);
        table.setTransferHandler(new TransferHandler(){

          public int getSourceActions(JComponent c) {
                return DnDConstants.ACTION_COPY_OR_MOVE;
            }

            public Transferable createTransferable(JComponent comp)
            {
                JTable table=(JTable)comp;
                int row=table.getSelectedRow();
                int col=table.getSelectedColumn();

                String value = (String)table.getModel().getValueAt(row,col);
                StringSelection transferable = new StringSelection(value);
                table.getModel().setValueAt(null,row,col);
                return transferable;
            }
            public boolean canImport(TransferHandler.TransferSupport info){
                if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)){
                    return false;
                }

                return true;
            }

            public boolean importData(TransferSupport support) {

                if (!support.isDrop()) {
                    return false;
                }

                if (!canImport(support)) {
                    return false;
                }

                JTable table=(JTable)support.getComponent();
                DefaultTableModel tableModel=(DefaultTableModel)table.getModel();

               JTable.DropLocation dl = (JTable.DropLocation)support.getDropLocation();

                int row = dl.getRow();
                int col=dl.getColumn();

                String data;
                try {
                    data = (String)support.getTransferable().getTransferData(DataFlavor.stringFlavor);
                } catch (UnsupportedFlavorException e) {
                    return false;
                } catch (IOException e) {
                    return false;
                }

                tableModel.setValueAt(data, row, col);

                return true;
            }

        });

        JFrame jf = new JFrame();
      Container c =  jf.getContentPane();
         c.add(table);
        jf.setSize(500,500);
        jf.setVisible(true);


    }

}