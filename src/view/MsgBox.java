/*
 * 2014 AA/HP
 * Pablo Ingaramo
 */
package view;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JOptionPane;

public final class MsgBox
{
    public static void info(String message) {
        info(message, callerMethod());
    }
    public static void info(String message, String caller) {
        show(message, caller, JOptionPane.INFORMATION_MESSAGE);
    }

    static void error(String message) {
        error(message, callerMethod());
    }
    public static void error(String message, String caller) {
        show(message, caller, JOptionPane.ERROR_MESSAGE);
    }

    public static void show(String message, String title, int iconId) {
        setClipboard(title+":"+message);
        JOptionPane.showMessageDialog(null, message, title, iconId);
    }


    public static String callerMethod() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

    public static void setClipboard(String message) {
        CLIPBOARD.setContents(new StringSelection(message), null);
        // nb: we don't respond to the "your content was splattered"
        //     event, so it's OK to pass a null owner.
    }
    private static final Toolkit AWT_TOOLKIT = Toolkit.getDefaultToolkit();
    private static final Clipboard CLIPBOARD = AWT_TOOLKIT.getSystemClipboard();

}
