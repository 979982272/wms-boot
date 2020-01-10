package com.tudou.base.menu.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单结点
 *
 * @param <k>
 * @param <v>
 */
public class Node<k, v> {
    // 链表结构 不进行序列化处理
    transient private Entry[] tables;
    static final int ARRAYSIZE = 16;
    private v text;
    private v url;
    private boolean encoded = false;
    private List<Node> items;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public v getText() {
        return text;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public v getUrl() {
        return url;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public List<Node> getItems() {
        return items;
    }

    public boolean isEncoded() {
        return encoded;
    }

    /**
     * 将所有的值转化为List
     *
     * @return
     */
    public List<Node<k, v>> toCollection() {
        List<Node<k, v>> nodes = new ArrayList<>();
        Node<k, v> node = null;
        for (Entry<k, v> entry : tables) {
            if (entry != null) {
                node = entryTransMenuNode(entry);
                nodes.add(node);
            }
        }
        return nodes;
    }

    /**
     * 遍历转化所有结点
     *
     * @param entry
     * @return
     */
    private Node<k, v> entryTransMenuNode(Entry<k, v> entry) {
        Node<k, v> node = new Node<>();
        Node<k, v> childNode = null;
        node.text = entry.val;
        if (entry.url != null) {
            node.url = (v) ("javascript:addTab(\"" + node.text + "\",\"" + (String) entry.url + "\")");
        }
        if (entry.ico != null) {
            node.text = (v) ("<span class=\'" + entry.ico + "\'></span>" + (String) entry.val);
        }
        List<Node> childNodeList = new ArrayList<>();
        for (Entry e : entry.next.tables) {
            if (null != e) {
                childNode = entryTransMenuNode(e);
                childNodeList.add(childNode);
            }
        }
        node.items = childNodeList;
        // 如果没有子节点直接给NULL
        if (CollectionUtils.isEmpty(childNodeList)) {
            node.items = null;
        }
        return node;
    }


    /**
     * 把值传递进来
     *
     * @param key
     * @param parentKey
     * @param text
     */
    public void put(k key, k parentKey, v text, v url, v ico) {
        int hash = hash(key.hashCode());
        int i = indexFor(hash, tables.length);
        // 判断原有的里边是否有这个值
        if (parentKey != null) {
            // 使用父id的索引值便于快速查找
            hash = hash(parentKey.hashCode());
            i = indexFor(hash, tables.length);
            Node<k, v> newNode = traverseEntryToNode(tables, i, parentKey);
            addChildEntry(key, text, url, ico, newNode);
            return;
        }
        addEntry(key, parentKey, text, url, ico, i);
    }

    /**
     * 遍历结点的Node
     *
     * @param tables
     * @param i
     * @param key
     * @return
     */
    private Node<k, v> traverseEntryToNode(Entry[] tables, int i, k key) {
        Node<k, v> node = null;
        for (Entry e : tables) {
            // 直接拿索引值位置去比较
            if (tables[i] != null) {
                Entry<k, v> entry = tables[i];
                if (entry.key.equals(key)) {
                    node = entry.next;
                    break;
                }
            }
            if (node != null) {
                return node;
            }
            if (null != e && e.key.equals(key)) {
                node = e.next;
                break;
            }
            if (null != e) {
                node = traverseEntryToNode(e.next.tables, i, key);
            }
        }
        return node;
    }

    /**
     * 添加子节点的entry
     *
     * @param key
     * @param value
     * @param node
     */
    private void addChildEntry(k key, v value, v url, v ico, Node node) {
        Entry[] t = node.tables;
        int hash = hash(key.hashCode());
        int i = indexFor(hash, tables.length);
        Node<k, v> n = new Node<k, v>();
        t[i] = new Entry(key, value, url, ico, n);
    }

    /**
     * 添加entry
     *
     * @param key
     * @param parentKey
     * @param value
     * @param i
     */
    private void addEntry(k key, k parentKey, v value, v url, v ico, int i) {
        Entry<k, v> e = tables[i];
        Node<k, v> nextNode = new Node<k, v>();
        tables[i] = new Entry<k, v>(key, value, url, ico, nextNode);
    }


    /**
     * 返回索引值
     *
     * @param hash
     * @param length
     * @return
     */
    private int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    /**
     * 计算hash值
     *
     * @param h
     * @return
     */
    private int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    public Node() {
        tables = new Entry[this.ARRAYSIZE];
    }

    /**
     * 初始化指定数组大小
     *
     * @param arraysize
     */
    public Node(int arraysize) {
        tables = new Entry[arraysize];
    }

    /**
     * 菜单链表结构
     * @param <k>
     * @param <v>
     */
    static class Entry<k, v> {
        k key;
        v val;
        v url;
        v ico;
        Node<k, v> next;

        private Entry() {
        }

        public Entry(k key, v val, v url, v ico, Node<k, v> next) {
            this.key = key;
            this.val = val;
            this.url = url;
            this.ico = ico;
            this.next = next;
        }
    }
}
