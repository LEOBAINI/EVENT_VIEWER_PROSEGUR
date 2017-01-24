package Clases;
import java.awt.Component;
import java.awt.Color;


import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.log4j.Logger;


public class RenderCelda extends DefaultTableCellRenderer 
{
	 final static Logger logger = Logger.getLogger(RenderCelda.class); 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column) 
    {
		
        Component cell = super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
        try{
       int colorNum=Integer.parseInt(String.valueOf(table.getValueAt(row,table.getColumnCount()-1)));
       String color=ColorPrioridad.darColor(colorNum);
       
 
            if(color.contains("red")){               
                cell.setForeground(Color.red.brighter());               
          }
            else if(color.contains("blue")){             
                cell.setForeground(Color.blue.brighter());
             }
            else if(color.contains("black")){               
                cell.setForeground(Color.black.brighter());
                }
            else if(color.contains("Cyan")){             
                cell.setForeground(Color.cyan.brighter());
                }
            else if(color.contains("brown")){             
                cell.setForeground(Color.darkGray);
                }
            else if(color.contains("gray")){             
                cell.setForeground(Color.gray);
                }
            else if(color.contains("green")){             
                cell.setForeground(Color.green.darker());
                }
            else if(color.contains("magenta")){             
                cell.setForeground(Color.magenta);
                }
        }catch(Exception e)
        {
        	
        	
        	logger.error(e.getMessage());
        }
            
        return cell;
    }
}