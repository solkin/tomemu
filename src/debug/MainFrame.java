/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package debug;

import com.tomclaw.tcuilite.*;

/**
 * @author solkin
 */
public class MainFrame extends Window {

    public boolean theme = false;

    public MainFrame() {
        super(MidletMain.screen);

        soft = new Soft(MidletMain.screen);

        soft.leftSoft = new PopupItem("Left") {

            public void actionPerformed() {
                MidletMain.screen.setActiveWindow(s_prevWindow);
            }
        };

    /*Group group = new Group();
     group.columnCount = 3;
     group.isShowGroups = true;
    
     GroupHeader groupHeader1 = new GroupHeader("Группа 1");
     groupHeader1.addChild( new GroupChild("Элемент 1-1") );
     groupHeader1.addChild( new GroupChild("Элемент 1-2") );
     groupHeader1.addChild( new GroupChild("Элемент 1-3") );
     groupHeader1.addChild( new GroupChild("Элемент 1-1") );
     groupHeader1.addChild( new GroupChild("Элемент 1-2") );
     groupHeader1.addChild( new GroupChild("Элемент 1-3") );
     groupHeader1.addChild( new GroupChild("Элемент 1-1") );
     groupHeader1.addChild( new GroupChild("Элемент 1-2") );
     groupHeader1.addChild( new GroupChild("Элемент 1-3") );
     group.addHeader( groupHeader1 );
     GroupHeader groupHeader2 = new GroupHeader("Группа 2");
     groupHeader2.isGroupVisible = false;
     groupHeader2.isItemsVisible = true;
     groupHeader2.isCollapsed = true;
     groupHeader2.addChild( new GroupChild("Элемент 2-1") );
     groupHeader2.addChild( new GroupChild("Элемент 2-2") );
     groupHeader2.addChild( new GroupChild("Элемент 2-3") );
     groupHeader2.addChild( new GroupChild("Элемент 2-1") );
     groupHeader2.addChild( new GroupChild("Элемент 2-2") );
     groupHeader2.addChild( new GroupChild("Элемент 2-3") );
     groupHeader2.addChild( new GroupChild("Элемент 2-1") );
     groupHeader2.addChild( new GroupChild("Элемент 2-2") );
     groupHeader2.addChild( new GroupChild("Элемент 2-3") );
     group.addHeader( groupHeader2 );
     GroupHeader groupHeader3 = new GroupHeader("Группа 3");
     groupHeader3.addChild( new GroupChild("Элемент 3-1") );
     groupHeader3.addChild( new GroupChild("Элемент 3-2") );
     groupHeader3.addChild( new GroupChild("Элемент 3-3") );
     groupHeader3.addChild( new GroupChild("Элемент 3-1") );
     groupHeader3.addChild( new GroupChild("Элемент 3-2") );
     groupHeader3.addChild( new GroupChild("Элемент 3-3") );
     groupHeader3.addChild( new GroupChild("Элемент 3-1") );
     groupHeader3.addChild( new GroupChild("Элемент 3-2") );
     groupHeader3.addChild( new GroupChild("Элемент 3-3") );
     group.addHeader( groupHeader3 );
    
     setGObject(group);*/
    /*Pane pane = new Pane(null, false);
    
     pane.addItem( new Label("Надпись здесь. Очень длинная и даже не вполне ясно, для чего.") );
     Field field = new Field("Поле ввода");
     field.setFocused( true );
     pane.addItem( field );
     Label label = new Label("---------------------------------");
     label.setVisible( false );
     label.setFocusable( true );
     pane.addItem( label );
     pane.addItem( new Check("Чек-бокс", true) );
     pane.addItem( new Label("Ещё одна коротенькая надпись.") );
    
     setGObject(pane);*/

    /*Grid grid = new Grid(null, false);
     grid.columns = 2;
     grid.isShowGrid = true;
     grid.itemWidth = screen.getWidth() / 2-3;
    
    
     grid.addItem( new Label("Надпись") );
     Field field1 = new Field("Поле ввода");
     field1.setFocused( true );
     grid.addItem( field1 );
     grid.addItem( new Label("Надпись") );
     Field field2 = new Field("Поле ввода");
     field2.setFocused( true );
     grid.addItem( field2 );
    
     setGObject(grid);*/
        Tab tab = new Tab(screen);
        Pane pane = new Pane(null, false);

        pane.addItem(new Button("Wait screen") {

            public void actionPerformed() {
                screen.setWaitScreenState(true);
                new Thread() {

                    public void run() {
                        try {
                            sleep(5000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        MidletMain.screen.setActiveWindow(s_prevWindow);
                        screen.setWaitScreenState(false);
                    }
                }.start();
            }
        });

        pane.addItem(new Button("Theme change") {

            public void actionPerformed() {
                new Thread() {

                    public void run() {
                        if (theme) {
                            System.out.println(Theme.startThemeChange("/res/themes/tcuilite_grey.tth", "/res/themes/tcuilite_def.tth"));
                        } else {
                            System.out.println(Theme.startThemeChange("/res/themes/tcuilite_def.tth", "/res/themes/tcuilite_grey.tth"));
                        }
                        theme = !theme;
                        // Theme.loadTheme( "/res/themes/tcuilite_grey.tth" );
                        // Theme.applyData();
                    }
                }.start();
            }
        });

        pane.addItem(new Button("Status") {

            public void actionPerformed() {
                new Thread() {

                    public void run() {
                        System.out.println(
                                Theme.isThemeChangeActive);
                    }
                }.start();
            }
        });

        pane.addItem(new Field("Field"));
        pane.addItem(new Gauge("Gauge"));

        tab.addTabItem(new TabItem("Title 1", 0, -1));
        tab.addTabItem(new TabItem("Title 2", 0, -1));
        TabItem tabItem = new TabItem("Title 3", 0, -1);
        tabItem.tabLabel = new Label("This is a tab title, it may be rather long or not. In any case it will be painted. ");
        tabItem.tabLabel.setHeader(true);
        tab.addTabItem(tabItem);

        tab.setGObject(pane);

        tab.switchTabTo(0);

        setGObject(tab);
    }
}
