package com.stone.java001;

public class SinglyLinkedList {
    
    private SinglyNode head = null;
    
    public void insert(Integer data){
        SinglyNode node = new SinglyNode(data);
        if(head == null){
            head = node;
        }else {
            SinglyNode node1 = head;
            while (node1.getNext()!=null){
                node1 = node1.getNext();
            }
            node.setNext(node1.getNext());
            node1.setNext(node);
        }
    }

    public void printAll(SinglyNode n) {
        SinglyNode node = n;
        while (node != null) {
            System.out.print(node.getData() + ",");
            node = node.getNext();
        }
        System.out.println();
    }

    public void isSingly(){
            if (head == null){

            }else {
                System.out.println("开始执行找到中间节点");
                SinglyNode p = head;
                SinglyNode q = head;
                if (p.getNext() == null) {
                    System.out.println("只有一个元素");

                }
                while (q.getNext() != null && q.getNext().getNext() != null) {
                    p = p.getNext();
                    q = q.getNext().getNext();

                }

                System.out.println("中间节点" + p.getData());
                System.out.println("开始执行奇数节点的回文判断");
                SinglyNode leftLink = null;
                SinglyNode rightLink = null;
                if (q.getNext() == null) {
                    //　p 一定为整个链表的中点，且节点数目为奇数
                    rightLink = p.getNext();
                    leftLink = inverseLinkList(p).getNext();
                    System.out.println("左边第一个节点" + leftLink.getData());
                    System.out.println("右边第一个节点" + rightLink.getData());

                } else {
                    //p q　均为中点
                    rightLink = p.getNext();
                    leftLink = inverseLinkList(p);
                }
                printAll(leftLink);
                System.out.println("=====");
                printAll(rightLink);
            }
    }

    public SinglyNode inverseLinkList(SinglyNode p){

        SinglyNode pre = null;
        SinglyNode r = head;
        System.out.println("z---" + r.getData());
        SinglyNode next= null;
        while(r !=p){
            next = r.getNext();

            r.setNext(pre);
            pre = r;
            r = next;
        }

        r.setNext(pre);
        //　返回左半部分的中点之前的那个节点
        //　从此处开始同步像两边比较
        return r;

    }

    public static void main(String[] args) {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.insert(1);
        singlyLinkedList.insert(2);
        singlyLinkedList.insert(3);
        singlyLinkedList.insert(2);
        singlyLinkedList.insert(1);
        singlyLinkedList.isSingly();
    }


}

class SinglyNode {
    private Integer data;
    private SinglyNode next;

    public SinglyNode() {
        this.next = null;
    }

    public SinglyNode(Integer data) {
        this.data = data;
    }

    public SinglyNode(Integer data, SinglyNode next) {
        this.data = data;
        this.next = next;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public SinglyNode getNext() {
        return next;
    }

    public void setNext(SinglyNode next) {
        this.next = next;
    }
}
