package Inicio;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
public class RenderCelda extends DefaultTableCellRenderer 
{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column) 
    {
        Component cell = super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
       int colorNum=Integer.parseInt(String.valueOf(table.getValueAt(row,table.getColumnCount()-1)));
       String color=ColorPrioridad.darColor(colorNum);
       
       
            if(color.equals("red")) // value.equals("red") )
            {
               // cell.setBackground(Color.red.brighter());
                cell.setForeground(Color.red.brighter());
                System.out.println(cell.getY());
            }
            else if(color.equals("blue")){
              //cell.setBackground(Color.blue.brighter());
                    cell.setForeground(Color.blue.brighter());
             }
            else if(color.equals("black")){
                // cell.setBackground(Color.black.brighter());
                       cell.setForeground(Color.black.brighter());
                }
            else if(color.equals("Dark Cyan")){
              //   cell.setBackground(Color.cyan.brighter());
                       cell.setForeground(Color.cyan.brighter());
                }
    
            
            
        return cell;
    }
}