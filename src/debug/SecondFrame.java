/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package debug;

import com.tomclaw.tcuilite.*;

/**
 * @author solkin
 */
public class SecondFrame extends Window {

    public SecondFrame() {
        super(MidletMain.screen);

        soft = new Soft(MidletMain.screen);

        soft.leftSoft = new PopupItem("Left") {

            public void actionPerformed() {
                MidletMain.screen.setActiveWindow(s_nextWindow);
                Dialog resultDialog = new Dialog(MidletMain.screen, null, title, "Текст в окошке");
                s_nextWindow.showDialog(resultDialog);
            }
        };
        Pane pane = new Pane(this, true);
        pane.addItem(new Label("Надпись на втором экране"));
        for (int c = 0; c < 100; c++) {
            ChatItem chatItem = new ChatItem(pane, "7068514", "Solkin", "Date/Time", ChatItem.TYPE_PLAIN_MSG, "[p]Текст [b]сообщения[/b][/p]");
            pane.addItem(chatItem);
        }
        setGObject(pane);
    }
}
