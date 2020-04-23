package com.stone.java001;

public class LRUForLinkList {

    private LRUNode headNode;
    // 容量大小，类比与redis设置的maxmemory大小
    private Integer capacity;
    // 实际元素长度
    private Integer length;

    public LRUForLinkList() {
        this.headNode = new LRUNode();
        this.capacity = 10;
        this.length = 0;
    }

    //插入数据
    public void add(Integer data) {
        //判断要插入的数据是否存在，如果存在，删除存在的元素，将该元素插入进头部
        //如果不存在，判断是否超出容量，如果超出容量，删除最后一个元素，将该元素插入头部
        LRUNode preNode = findPreNode(data);
        if (preNode != null) {
            delNode(preNode);
            insertBegin(data);
        } else {
            if (length >= capacity) {
                delEndNode();
            }
            insertBegin(data);
        }
    }

    //在头部插入元素
    public void insertBegin(Integer data) {
        this.headNode.setNext(new LRUNode(data, this.headNode.getNext()));
        length++;
    }

    //找到该元素的前一个节点
    public LRUNode findPreNode(Integer data) {
        LRUNode node = this.headNode;
        while (node.getNext() != null) {
            if (data.equals(node.getNext().getData())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    //删除指定值的元素
    public void delNode(LRUNode preNode) {
        LRUNode tmp = preNode.getNext();
        preNode.setNext(tmp.getNext());
        tmp = null;
        length--;
    }

    //删除末尾节点
    public void delEndNode() {
        LRUNode ptr = headNode;
        if (ptr.getNext() == null) {
            return;
        }

        while (ptr.getNext().getNext() != null) {
            ptr = ptr.getNext();
        }

        LRUNode tmp = ptr.getNext();
        ptr.setNext(null);
        tmp = null;
        length--;
    }

    //打印该链表
    private void printAll() {
        LRUNode node = headNode.getNext();
        while (node != null) {
            System.out.print(node.getData() + ",");
            node = node.getNext();
        }
        System.out.println();
    }


    //测试。。。
    public static void main(String[] args) {
        LRUForLinkList lruForLinkList = new LRUForLinkList();
        for(int i=0;i<10;i++){
            lruForLinkList.add(i);
        }
        lruForLinkList.printAll();
        lruForLinkList.add(8);
        lruForLinkList.printAll();
        lruForLinkList.add(11);
        lruForLinkList.printAll();
    }
}

class LRUNode {
    private Integer data;
    private LRUNode next;

    public LRUNode() {
        this.next = null;
    }

    public LRUNode(Integer data) {
        this.data = data;
    }

    public LRUNode(Integer data, LRUNode next) {
        this.data = data;
        this.next = next;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public LRUNode getNext() {
        return next;
    }

    public void setNext(LRUNode next) {
        this.next = next;
    }
}
