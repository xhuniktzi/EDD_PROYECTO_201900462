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
    ListaEnlazadaSimple<Cliente> keys;
    int MinDeg;
    ListaEnlazadaSimple<BTreeNodeClient> children;
    int num;
    boolean isLeaf;

    // constructor
    public BTreeNodeClient(int deg, boolean isLeaf){

        this.MinDeg = deg;
        this.isLeaf = isLeaf;
        this.keys = new ListaEnlazadaSimple<>(2 * this.MinDeg);
        this.children = new ListaEnlazadaSimple<>(2 * this.MinDeg + 1);
        this.num = 0;
    }

    public int findKey(String key){
        int idx = 0;
        while (idx < num && keys.getByIndex(idx).dpi.compareTo(key) < 0)
            ++idx;
        return idx;
    }


    public void remove(String key){

        int idx = findKey(key);
        if (idx < num && keys.getByIndex(idx).dpi.equals(key)){
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
            
            if (children.getByIndex(idx).num < MinDeg)
                fill(idx);

            if (flag && idx > num)
                children.getByIndex(idx - 1).remove(key);
            else
                children.getByIndex(idx).remove(key);
        }
    }

    public void removeFromLeaf(int idx){
        for (int i = idx +1;i < num;++i)
            keys.setByIndex(i - 1, keys.getByIndex(i));
        num --;
    }

    public void removeFromNonLeaf(int idx){
        String key = keys.getByIndex(idx).dpi;
        if (children.getByIndex(idx).num >= MinDeg){
            Cliente pred = getPred(idx);
            keys.setByIndex(idx, pred);
            children.getByIndex(idx).remove(pred.dpi);
        }
        else if (children.getByIndex(idx + 1).num >= MinDeg){
            Cliente succ = getSucc(idx);
            keys.setByIndex(idx, succ);
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
        return cur.keys.getByIndex(cur.num-1);
    }

    public Cliente getSucc(int idx){
        BTreeNodeClient cur = children.getByIndex(idx + 1);
        while (!cur.isLeaf)
            cur = cur.children.getByIndex(0);
        return cur.keys.getByIndex(0);
    }

    public void fill(int idx){
        if (idx != 0 && children.getByIndex(idx - 1).num >= MinDeg)
            borrowFromPrev(idx);
        else if (idx != num && children.getByIndex(idx + 1).num >= MinDeg)
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
            child.keys.setByIndex(i + 1, child.keys.getByIndex(i));

        if (!child.isLeaf){
            for (int i = child.num; i >= 0; --i)
                child.children.setByIndex(i + 1, child.children.getByIndex(i));
        }

        child.keys.setByIndex(0, keys.getByIndex(idx - 1));
        if (!child.isLeaf)
            child.children.setByIndex(0, sibling.children.getByIndex(sibling.num));

        keys.setByIndex(idx - 1, sibling.keys.getByIndex(sibling.num - 1));
        child.num += 1;
        sibling.num -= 1;
    }

    public void borrowFromNext(int idx){

        BTreeNodeClient child = children.getByIndex(idx);
        BTreeNodeClient sibling = children.getByIndex(idx + 1);

        child.keys.setByIndex(child.num, keys.getByIndex(idx));

        if (!child.isLeaf)
            child.children.setByIndex(child.num + 1, sibling.children.getByIndex(0));

        keys.setByIndex(idx, sibling.keys.getByIndex(0));

        for (int i = 1; i < sibling.num; ++i)
            sibling.keys.setByIndex(i - 1, sibling.keys.getByIndex(i));

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

        child.keys.setByIndex(MinDeg - 1, keys.getByIndex(idx));

        for (int i =0 ; i< sibling.num; ++i)
            child.keys.setByIndex(i + MinDeg, sibling.keys.getByIndex(i));

        if (!child.isLeaf){
            for (int i = 0;i <= sibling.num; ++i)
                child.children.setByIndex(i + MinDeg, sibling.children.getByIndex(i));
        }

        for (int i = idx+1; i<num; ++i)
            keys.setByIndex(i - 1, keys.getByIndex(i));
        for (int i = idx+2;i<=num;++i)
            children.setByIndex(i - 1, children.getByIndex(i));

        child.num += sibling.num + 1;
        num--;
    }


    public void insertNotFull(Cliente key){
        int i = num -1;

        if (isLeaf){
            while (i >= 0 && keys.getByIndex(i).dpi.compareTo(key.dpi) > 0){
                keys.setByIndex(i + 1, keys.getByIndex(i));
                i--;
            }
            keys.setByIndex(i + 1, key);
            num = num +1;
        }
        else{
            while (i >= 0 && keys.getByIndex(i).dpi.compareTo(key.dpi) > 0)
                i--;
            if (children.getByIndex(i+1).num == 2*MinDeg - 1){
                splitChild(i+1,children.getByIndex(i + 1));
                if (keys.getByIndex(i + 1).dpi.compareTo(key.dpi) < 0)
                    i++;
            }
            children.getByIndex(i + 1).insertNotFull(key);
        }
    }


    public void splitChild(int i, BTreeNodeClient y){
        BTreeNodeClient z = new BTreeNodeClient(y.MinDeg,y.isLeaf);
        z.num = MinDeg - 1;

        for (int j = 0; j < MinDeg-1; j++)
            z.keys.setByIndex(j, y.keys.getByIndex(j + MinDeg));
        if (!y.isLeaf){
            for (int j = 0; j < MinDeg; j++)
                z.children.setByIndex(j, y.children.getByIndex(j + MinDeg));
        }
        y.num = MinDeg-1;

        for (int j = num; j >= i+1; j--)
            children.setByIndex(j + 1, children.getByIndex(j));
        children.setByIndex(i + 1, z);

        
        for (int j = num-1;j >= i;j--)
            keys.setByIndex(j + 1, keys.getByIndex(j));
        keys.setByIndex(i, y.keys.getByIndex(MinDeg - 1));

        num = num + 1;
    }


    public void traverse(){
        int i;
        for (i = 0; i< num; i++){
            if (!isLeaf)
                children.getByIndex(i).traverse();
            System.out.printf(" %s",keys.getByIndex(i).dpi);
        }

        if (!isLeaf){
            children.getByIndex(i).traverse();
        }
    }

    public BTreeNodeClient search(String key){
        int i = 0;
        while (i < num && key.compareTo(keys.getByIndex(i).dpi) > 0)
            i++;

        if (keys.getByIndex(i).dpi.equals(key))
            return this;
        if (isLeaf)
            return null;
        return children.getByIndex(i).search(key);
    }
}
