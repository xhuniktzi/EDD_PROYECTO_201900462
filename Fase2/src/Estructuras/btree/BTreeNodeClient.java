/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.btree;

import Modelos.Cliente;

/**
 *
 * @author Xhunik_Local
 */
public class BTreeNodeClient {
    ListaEnlazadaSimple<Cliente> dataEntries;
    int t;
    ListaEnlazadaSimple<BTreeNodeClient> children;
    int num;
    boolean isLeaf;

    // constructor
    public BTreeNodeClient(int deg, boolean isLeaf){

        this.t = deg;
        this.isLeaf = isLeaf;
        this.dataEntries = new ListaEnlazadaSimple<>(2 * this.t + 1); // 2 * t - 1
        this.children = new ListaEnlazadaSimple<>(2 * this.t); // 2 * t
        this.num = 0;
    }

    public int findKey(String key){
        int idx = 0;
        while (idx < num && dataEntries.getByIndex(idx).dpi.compareTo(key) < 0)
            ++idx;
        return idx;
    }


    public void remove(String key){

        int idx = findKey(key);
        if (idx < num && dataEntries.getByIndex(idx).dpi.equals(key)){
            if (isLeaf)
                removeFromLeaf(idx);
            else
                removeFromNonLeaf(idx);
        }
        else{
            if (isLeaf){
                System.out.printf("The key %d is does not exist in the tree\n",key);
                return;
            }
            boolean flag = idx == num; 
            
            if (children.getByIndex(idx).num < t)
                fill(idx);

            if (flag && idx > num)
                children.getByIndex(idx - 1).remove(key);
            else
                children.getByIndex(idx).remove(key);
        }
    }

    public void removeFromLeaf(int idx){
        for (int i = idx +1;i < num;++i)
            dataEntries.setByIndex(i - 1, dataEntries.getByIndex(i));
        num --;
    }

    public void removeFromNonLeaf(int idx){
        String key = dataEntries.getByIndex(idx).dpi;
        if (children.getByIndex(idx).num >= t){
            Cliente pred = getPred(idx);
            dataEntries.setByIndex(idx, pred);
            children.getByIndex(idx).remove(pred.dpi);
        }
        else if (children.getByIndex(idx + 1).num >= t){
            Cliente succ = getSucc(idx);
            dataEntries.setByIndex(idx, succ);
            children.getByIndex(idx + 1).remove(succ.dpi);
        }
        else{
            merge(idx);
            children.getByIndex(idx).remove(key);
        }
    }

    public Cliente getPred(int idx){
        BTreeNodeClient cur = children.getByIndex(idx);
        while (!cur.isLeaf)
            cur = cur.children.getByIndex(cur.num);
        return cur.dataEntries.getByIndex(cur.num-1);
    }

    public Cliente getSucc(int idx){
        BTreeNodeClient cur = children.getByIndex(idx + 1);
        while (!cur.isLeaf)
            cur = cur.children.getByIndex(0);
        return cur.dataEntries.getByIndex(0);
    }

    public void fill(int idx){
        if (idx != 0 && children.getByIndex(idx - 1).num >= t)
            borrowFromPrev(idx);
        else if (idx != num && children.getByIndex(idx + 1).num >= t)
            borrowFromNext(idx);
        else{
            if (idx != num)
                merge(idx);
            else
                merge(idx-1);
        }
    }

    public void borrowFromPrev(int idx){

        BTreeNodeClient child = children.getByIndex(idx);
        BTreeNodeClient sibling = children.getByIndex(idx - 1);

        for (int i = child.num-1; i >= 0; --i)
            child.dataEntries.setByIndex(i + 1, child.dataEntries.getByIndex(i));

        if (!child.isLeaf){
            for (int i = child.num; i >= 0; --i)
                child.children.setByIndex(i + 1, child.children.getByIndex(i));
        }

        child.dataEntries.setByIndex(0, dataEntries.getByIndex(idx - 1));
        if (!child.isLeaf)
            child.children.setByIndex(0, sibling.children.getByIndex(sibling.num));

        dataEntries.setByIndex(idx - 1, sibling.dataEntries.getByIndex(sibling.num - 1));
        child.num += 1;
        sibling.num -= 1;
    }

    public void borrowFromNext(int idx){

        BTreeNodeClient child = children.getByIndex(idx);
        BTreeNodeClient sibling = children.getByIndex(idx + 1);

        child.dataEntries.setByIndex(child.num, dataEntries.getByIndex(idx));

        if (!child.isLeaf)
            child.children.setByIndex(child.num + 1, sibling.children.getByIndex(0));

        dataEntries.setByIndex(idx, sibling.dataEntries.getByIndex(0));

        for (int i = 1; i < sibling.num; ++i)
            sibling.dataEntries.setByIndex(i - 1, sibling.dataEntries.getByIndex(i));

        if (!sibling.isLeaf){
            for (int i= 1; i <= sibling.num;++i)
                sibling.children.setByIndex(i - 1, sibling.children.getByIndex(i));
        }
        child.num += 1;
        sibling.num -= 1;
    }

    public void merge(int idx){

        BTreeNodeClient child = children.getByIndex(idx);
        BTreeNodeClient sibling = children.getByIndex(idx + 1);

        child.dataEntries.setByIndex(t - 1, dataEntries.getByIndex(idx));

        for (int i =0 ; i< sibling.num; ++i)
            child.dataEntries.setByIndex(i + t, sibling.dataEntries.getByIndex(i));

        if (!child.isLeaf){
            for (int i = 0;i <= sibling.num; ++i)
                child.children.setByIndex(i + t, sibling.children.getByIndex(i));
        }

        for (int i = idx+1; i<num; ++i)
            dataEntries.setByIndex(i - 1, dataEntries.getByIndex(i));
        for (int i = idx+2;i<=num;++i)
            children.setByIndex(i - 1, children.getByIndex(i));

        child.num += sibling.num + 1;
        num--;
    }


    public void insertNotFull(Cliente key){
        int i = num -1;

        if (isLeaf){
            while (i >= 0 && dataEntries.getByIndex(i).dpi.compareTo(key.dpi) > 0){
                dataEntries.setByIndex(i + 1, dataEntries.getByIndex(i));
                i--;
            }
            dataEntries.setByIndex(i + 1, key);
            num = num +1;
        }
        else{
            while (i >= 0 && dataEntries.getByIndex(i).dpi.compareTo(key.dpi) > 0)
                i--;
            if (children.getByIndex(i+1).num == 2*t - 1){
                splitChild(i+1,children.getByIndex(i + 1));
                if (dataEntries.getByIndex(i + 1).dpi.compareTo(key.dpi) < 0)
                    i++;
            }
            children.getByIndex(i + 1).insertNotFull(key);
        }
    }


    public void splitChild(int i, BTreeNodeClient y){
        BTreeNodeClient z = new BTreeNodeClient(y.t,y.isLeaf);
        z.num = t - 1;

        for (int j = 0; j < t-1; j++)
            z.dataEntries.setByIndex(j, y.dataEntries.getByIndex(j + t));
        if (!y.isLeaf){
            for (int j = 0; j < t; j++)
                z.children.setByIndex(j, y.children.getByIndex(j + t));
        }
        y.num = t-1;

        for (int j = num; j >= i+1; j--)
            children.setByIndex(j + 1, children.getByIndex(j));
        children.setByIndex(i + 1, z);

        
        for (int j = num-1;j >= i;j--)
            dataEntries.setByIndex(j + 1, dataEntries.getByIndex(j));
        dataEntries.setByIndex(i, y.dataEntries.getByIndex(t - 1));

        num = num + 1;
    }


    public void traverse(){
        int i;
        for (i = 0; i< num; i++){
            if (!isLeaf)
                children.getByIndex(i).traverse();
            System.out.printf(" %s",dataEntries.getByIndex(i).dpi);
        }

        if (!isLeaf){
            children.getByIndex(i).traverse();
        }
    }

    public BTreeNodeClient search(String key){
        int i = 0;
        while (i < num && key.compareTo(dataEntries.getByIndex(i).dpi) > 0)
            i++;

        if (dataEntries.getByIndex(i).dpi.equals(key))
            return this;
        if (isLeaf)
            return null;
        return children.getByIndex(i).search(key);
    }
}
