package Algorithm.MyHashMap;

import java.util.ArrayList;

public class MyHashTable {


    private int size = 16;
    private ArrayList<HashEntry> arrayList = new ArrayList<HashEntry>();


    public MyHashTable() {
        for (int i = 0; i < size; i++) {
            arrayList.add(i, null);
        }
    }

    public ArrayList<HashEntry> getContainer() {
        return arrayList;
    }

    public int getIndex(int key) {
        return key % 16;
    }

    public boolean insert(Integer key, int value) {
        if (get(key) != null) {
            return false;
        }
        int theLocIndex = getIndex(key);
        if (theLocIndex >= 16) {
            return false;
        }
        if (arrayList.get(theLocIndex) == null) {
            HashEntry curEntry = new HashEntry();
            curEntry.key = key;
            curEntry.value = value;
            arrayList.remove(theLocIndex);
            arrayList.add(theLocIndex, curEntry);
            return true;

        } else if (arrayList.get(theLocIndex) != null) {
            HashEntry cEntry = arrayList.get(theLocIndex);
            HashEntry preEntry1 = cEntry;
            while (cEntry != null) {
                preEntry1 = cEntry;
                cEntry = cEntry.next;
            }
            HashEntry newEntry = new HashEntry();
            newEntry.pre = cEntry;
            newEntry.key = key;
            newEntry.value = value;
            preEntry1.next = newEntry;
            return true;
        }
        return false;
    }

    public HashEntry get(Integer key) {
        int theLocIndex = getIndex(key);
        if (arrayList.get(theLocIndex) == null) {
            return null;
        } else {
            HashEntry cEntry = arrayList.get(theLocIndex);
            while (cEntry != null) {
                if (cEntry.key == key) {
                    return cEntry;
                } else {
                    cEntry = cEntry.next;
                }

            }
            return null;

        }
    }

    public boolean remove(int key) {
        int theLocIndex = getIndex(key);
        if (arrayList.get(theLocIndex) == null) {
            return false;
        } else {
            HashEntry cEntry = arrayList.get(theLocIndex);
            while (cEntry != null) {
                if (cEntry.key == key) {
                    if (cEntry.pre == null) {
                        arrayList.remove(theLocIndex);
                        return true;
                    } else {
                        cEntry.pre.next = cEntry.next;
                        return true;
                    }
                } else {
                    cEntry = cEntry.next;
                }

            }
            return false;

        }
    }

    public boolean edit(int key, int value) {
        HashEntry cEntry = get(key);
        if (cEntry == null) {
            return false;
        }
        cEntry.value = value;
        return true;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyHashTable MyHashTable = new MyHashTable();
        MyHashTable.insert(1, 2);
        MyHashTable.insert(17, 3);
        System.out.println(MyHashTable.get(1));
        System.out.println(MyHashTable.get(17));
    }

}
