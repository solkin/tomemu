package com.tomclaw.tcuilite;

import com.tomclaw.images.Splitter;
import com.tomclaw.utils.DrawUtil;

import javax.microedition.lcdui.Graphics;
import javax.microedition.rms.RecordStore;
import java.util.Vector;

/**
 * Solkin Igor Viktorovich, TomClaw Software, 2003-2013
 * http://www.tomclaw.com/
 *
 * @author Solkin
 */
public class List extends Scroll {

    public RecordStore recordStore;
    public ListRmsRenderer listRmsRenderer;
    public Vector items = null;
    public int selectedIndex = 0;
    public ListEvent listEvent = null;
    /**
     * Runtime
     **/
    public int startIndex = 0;
    public boolean isSelectedState = false;
    /**
     * Colors
     **/
    public static int foreColor = 0x555555;
    public static int foreSelColor = 0x555555;
    public static int backColor = 0xFFFFFF;
    public static int hrLine = 0xDDDDDD;
    public static int selectedGradFrom = 0xDDDDFF;
    public static int selectedGradTo = 0xBBAAEE;
    public static int selectedUpOutline = 0xCCCCEE;
    public static int selectedBottomOutline = 0xAAAACC;
    /**
     * Sizes
     **/
    public int itemHeight;
    private ListItem templistItem;
    private int imageOffset = 0;

    public List() {
        items = new Vector();
    }

    public List(String fileName) {
        items = new Vector();
        openRecordStore(fileName);
    }

    public void addItem(ListItem listItem) {
        items.addElement(listItem);
        if (recordStore != null) {
            byte[] abyte0 = listRmsRenderer.getRmsData(listItem);
            try {
                recordStore.addRecord(abyte0, 0, abyte0.length);
            } catch (Throwable ex) {
            }
        }
    }

    public void repaint(Graphics g) {
        repaint(g, 0, 0);
    }

    public void repaint(Graphics g, int paintX, int paintY) {
        itemHeight = Theme.font.getHeight() + Theme.upSize * 2;
        if (!items.isEmpty() && items.size() > height / itemHeight) {
            repaintScrollWidth = Theme.scrollWidth;
            if (selectedIndex == -1) {
                selectedIndex = 0;
            }
        } else if (items.isEmpty()) {
            selectedIndex = -1;
        } else {
            repaintScrollWidth = -1;
            if (selectedIndex == -1) {
                selectedIndex = 0;
            }
        }
        /** Checking list position **/
        if (selectedIndex >= items.size()) {
            selectedIndex = items.size() - 1;
        }
        if (yOffset != 0 && items.size() * itemHeight - yOffset < height) {
            if (items.size() * itemHeight > height) {
                yOffset = items.size() * itemHeight - height;
            } else {
                yOffset = 0;
            }
        }
        g.setFont(Theme.font);
        g.setColor(backColor);
        g.fillRect(paintX + x, paintY + y, width - repaintScrollWidth, height);
        startIndex = (yOffset / itemHeight);
        for (int c = startIndex; c < startIndex + height / itemHeight + 2; c++) {
            if (c == selectedIndex) {
                DrawUtil.fillVerticalGradient(g, paintX + x, paintY + y + c * itemHeight - yOffset, width - 1 - repaintScrollWidth - x, itemHeight, selectedGradFrom, selectedGradTo);
                g.setColor(selectedUpOutline);
                g.drawLine(paintX + x, paintY + y + c * itemHeight - yOffset, paintX + x + width - 2 - 1 - repaintScrollWidth + 1, paintY + y + c * itemHeight - yOffset);
                g.setColor(selectedBottomOutline);
                g.drawLine(paintX + x, paintY + y + (c + 1) * itemHeight - yOffset, paintX + x + width - 2 - 1 - repaintScrollWidth + 1, paintY + y + (c + 1) * itemHeight - yOffset);
                g.setColor(foreSelColor);
            } else {
                g.setColor(hrLine);
                g.drawLine(paintX + x, paintY + y + (c + 1) * itemHeight - yOffset, paintX + x + 1 + width - 2 - repaintScrollWidth, paintY + y + (c + 1) * itemHeight - yOffset);
                g.setColor(foreColor);
            }
            if (c < items.size()) {
                templistItem = getElement(c);
                if (templistItem.imageFileHash != 0) {
                    imageOffset = Splitter.drawImage(g, templistItem.imageFileHash, templistItem.imageIndex, paintX + x + Theme.upSize + 1, paintY + y + c * itemHeight - yOffset + itemHeight / 2 + 1, true);
                    if (imageOffset > 0) {
                        imageOffset += Theme.upSize;
                    }
                } else {
                    imageOffset = 0;
                }
                g.drawString(templistItem.title,
                        paintX + x + Theme.upSize + 1 + imageOffset,
                        paintY + y + c * itemHeight - yOffset + Theme.upSize + 1,
                        Graphics.TOP | Graphics.LEFT);
                if (templistItem.descr != null) {
                    g.setFont(Theme.titleFont);
                    g.drawString(templistItem.descr,
                            paintX + x + width - Theme.titleFont.stringWidth(templistItem.descr) - repaintScrollWidth - Theme.upSize,
                            paintY + y + c * itemHeight - yOffset + Theme.upSize + 1,
                            Graphics.TOP | Graphics.LEFT);
                    g.setFont(Theme.font);
                }
            }
        }
        /** Scroll **/
        this.totalHeight = items.size() * itemHeight;
        super.repaint(g, paintX, paintY);
    }

    public void keyPressed(int keyCode) {
        if (items.isEmpty()) {
            selectedIndex = -1;
            return;
        }
        if (Screen.getExtGameAct(keyCode) == Screen.UP) {
            if (selectedIndex == 0) {
                selectedIndex = items.size() - 1;
                yOffset = itemHeight * items.size() - height + 1;
                if (yOffset < 0) {
                    yOffset = 0;
                }
                return;
            }
            selectedIndex--;
            if (yOffset > selectedIndex * itemHeight) {
                yOffset = (selectedIndex) * itemHeight;
            }
        }
        if (Screen.getExtGameAct(keyCode) == Screen.DOWN) {
            if (selectedIndex == items.size() - 1) {
                selectedIndex = 0;
                yOffset = 0;
                return;
            }
            selectedIndex++;
            if (yOffset + height < (selectedIndex + 1) * itemHeight + 1) {
                yOffset = (selectedIndex + 1) * itemHeight - height + 1;
            }
        }
        if (Screen.getExtGameAct(keyCode) == Screen.FIRE) {
            if (selectedIndex != -1 && selectedIndex < items.size()) {
                (getElement(selectedIndex)).actionPerformed();
                if (listEvent != null) {
                    listEvent.actionPerformed(getElement(selectedIndex));
                }
            }
        }
    }

    public void keyReleased(int keyCode) {
    }

    public void keyRepeated(int keyCode) {
        keyPressed(keyCode);
    }

    public void pointerPressed(int x, int y) {
        if (items.isEmpty()) {
            setSelectedIndex(-1);
            return;
        }
        if (x < this.x || y < this.y || x > this.x + width || y > this.y + height) {
            return;
        }
        if (x > this.x + width - repaintScrollWidth) {
            isScrollAction = true;
        } else {
            isScrollAction = false;
            setSelectedIndex((-this.y + yOffset + y) / itemHeight);
            if (selectedIndex > items.size() - 1) {
                setSelectedIndex(items.size() - 1);
            }
            if (isSelectedState && selectedIndex != -1 && selectedIndex < items.size()) {
                (getElement(selectedIndex)).actionPerformed();
                if (listEvent != null) {
                    listEvent.actionPerformed(getElement(selectedIndex));
                }
            }
            isSelectedState = true;
        }
    }

    public void pointerReleased(int x, int y) {
        prevYDrag = -1;
    }

    public boolean pointerDragged(int x, int y) {
        if (items.isEmpty()) {
            setSelectedIndex(-1);
            return false;
        }
        if (isScrollAction) {
            scrollStart = y - this.y - scrollHeight / 2;
            yOffset = scrollStart * (items.size() * itemHeight) / height;
            if (yOffset < 0) {
                yOffset = 0;
            } else if (yOffset > (items.size()) * itemHeight - height + 1) {
                yOffset = (items.size()) * itemHeight - height + 1;
            } else {
                return true;
            }
            return false;
        } else if (repaintScrollWidth > 0) {
            if (prevYDrag == -1) {
                prevYDrag = yOffset + y;
                return true;
            }
            yOffset = prevYDrag - y;
            if (yOffset < 0) {
                yOffset = 0;
            } else if (yOffset > (items.size()) * itemHeight - height + 1) {
                yOffset = (items.size()) * itemHeight - height + 1;
            } else {
                return true;
            }
            return false;
        }
        return true;
    }

    private boolean openRecordStore(String fileName) {
        try {
            recordStore = RecordStore.openRecordStore(fileName, false);
            items.setSize(recordStore.getNumRecords());
            return true;
        } catch (Throwable ex) {
            recordStore = null;
            return false;
        }
    }

    public ListItem getElement(int index) {
        if (items.elementAt(index) != null) {
            return (ListItem) items.elementAt(index);
        } else if (recordStore != null) {
            try {
                byte[] abyte0 = recordStore.getRecord(index + 1);
                ListItem listItem = listRmsRenderer.getRmsItem(abyte0);
                items.setElementAt(listItem, index);
                return listItem;
            } catch (Throwable ex) {
            }
            return null;
        }
        return null;
    }

    public void setSelectedIndex(int selectedIndex) {
        if (selectedIndex != this.selectedIndex) {
            isSelectedState = false;
            this.selectedIndex = selectedIndex;
        }
    }
}
