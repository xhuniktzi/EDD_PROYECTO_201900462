
package Estructuras.btree;

import Modelos.Cliente;

public class BTreeNodeClient {
    ListaEnlazadaSimple<Cliente> dataEntries;
    private final int t;
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

    public int findKey(String dpi){
        int index = 0;
        while (index < num && dataEntries.getByIndex(index).dpi.compareTo(dpi) < 0)
            ++index;
        return index;
    }


    public void remove(String dpi){
        int index = findKey(dpi);
        if (index < num && dataEntries.getByIndex(index).dpi.equals(dpi)){
            if (isLeaf){
                for (int i = index +1;i < num;++i)
                    dataEntries.setByIndex(i - 1, dataEntries.getByIndex(i));
                num--;
            } else{
                if (children.getByIndex(index).num >= t){
                    Cliente pred = getPrev(index);
                    dataEntries.setByIndex(index, pred);
                    children.getByIndex(index).remove(pred.dpi);
                }
                else if (children.getByIndex(index + 1).num >= t){
                    Cliente succ = getNext(index);
                    dataEntries.setByIndex(index, succ);
                    children.getByIndex(index + 1).remove(succ.dpi);
                }
                else{
                    union(index);
                    children.getByIndex(index).remove(dpi);
                }
            }
        } else {
            if (isLeaf)
                return;
            boolean flag = index == num; 
            
            if (children.getByIndex(index).num < t)
                fill(index);

            if (flag && index > num)
                children.getByIndex(index - 1).remove(dpi);
            else
                children.getByIndex(index).remove(dpi);
        }
    }

    private Cliente getPrev(int index){
        BTreeNodeClient cur = children.getByIndex(index);
        while (!cur.isLeaf)
            cur = cur.children.getByIndex(cur.num);
        return cur.dataEntries.getByIndex(cur.num-1);
    }

    private Cliente getNext(int index){
        BTreeNodeClient cur = children.getByIndex(index + 1);
        while (!cur.isLeaf)
            cur = cur.children.getByIndex(0);
        return cur.dataEntries.getByIndex(0);
    }

    private void fill(int index){
        if (index != 0 && children.getByIndex(index - 1).num >= t){
            BTreeNodeClient child = children.getByIndex(index);
            BTreeNodeClient sibling = children.getByIndex(index - 1);

            for (int i = child.num-1; i >= 0; --i)
                child.dataEntries.setByIndex(i + 1, child.dataEntries.getByIndex(i));

            if (!child.isLeaf){
                for (int i = child.num; i >= 0; --i)
                    child.children.setByIndex(i + 1, child.children.getByIndex(i));
            }

            child.dataEntries.setByIndex(0, dataEntries.getByIndex(index - 1));
            if (!child.isLeaf)
                child.children.setByIndex(0, sibling.children.getByIndex(sibling.num));

            dataEntries.setByIndex(index - 1, sibling.dataEntries.getByIndex(sibling.num - 1));
            child.num += 1;
            sibling.num -= 1;
        }
        else if (index != num && children.getByIndex(index + 1).num >= t){
            BTreeNodeClient child = children.getByIndex(index);
           BTreeNodeClient sibling = children.getByIndex(index + 1);

           child.dataEntries.setByIndex(child.num, dataEntries.getByIndex(index));

           if (!child.isLeaf)
               child.children.setByIndex(child.num + 1, sibling.children.getByIndex(0));

           dataEntries.setByIndex(index, sibling.dataEntries.getByIndex(0));

           for (int i = 1; i < sibling.num; ++i)
               sibling.dataEntries.setByIndex(i - 1, sibling.dataEntries.getByIndex(i));

           if (!sibling.isLeaf){
               for (int i= 1; i <= sibling.num;++i)
                   sibling.children.setByIndex(i - 1, sibling.children.getByIndex(i));
           }
           child.num += 1;
           sibling.num -= 1;
        }
        else{
            if (index != num)
                union(index);
            else
                union(index-1);
        }
    }

    private void union(int index){

        BTreeNodeClient child = children.getByIndex(index);
        BTreeNodeClient sibling = children.getByIndex(index + 1);

        child.dataEntries.setByIndex(t - 1, dataEntries.getByIndex(index));

        for (int i =0 ; i< sibling.num; ++i)
            child.dataEntries.setByIndex(i + t, sibling.dataEntries.getByIndex(i));

        if (!child.isLeaf){
            for (int i = 0;i <= sibling.num; ++i)
                child.children.setByIndex(i + t, sibling.children.getByIndex(i));
        }

        for (int i = index+1; i<num; ++i)
            dataEntries.setByIndex(i - 1, dataEntries.getByIndex(i));
        for (int i = index+2;i<=num;++i)
            children.setByIndex(i - 1, children.getByIndex(i));

        child.num += sibling.num + 1;
        num--;
    }


    public void insertNotFull(Cliente c){
        int i = num -1;

        if (isLeaf){
            while (i >= 0 && dataEntries.getByIndex(i).dpi.compareTo(c.dpi) > 0){
                dataEntries.setByIndex(i + 1, dataEntries.getByIndex(i));
                i--;
            }
            dataEntries.setByIndex(i + 1, c);
            num = num +1;
        }
        else{
            while (i >= 0 && dataEntries.getByIndex(i).dpi.compareTo(c.dpi) > 0)
                i--;
            if (children.getByIndex(i+1).num == 2*t - 1){
                split(i+1,children.getByIndex(i + 1));
                if (dataEntries.getByIndex(i + 1).dpi.compareTo(c.dpi) < 0)
                    i++;
            }
            children.getByIndex(i + 1).insertNotFull(c);
        }
    }


    public void split(int i, BTreeNodeClient y){
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


    public void printTree(){
        int i;
        for (i = 0; i< num; i++){
            if (!isLeaf)
                children.getByIndex(i).printTree();
            System.out.printf(" %s",dataEntries.getByIndex(i).dpi);
        }

        if (!isLeaf){
            children.getByIndex(i).printTree();
        }
    }

    public BTreeNodeClient findClienteByDpi(String dpi){
        int i = 0;
        while (i < num && dpi.compareTo(dataEntries.getByIndex(i).dpi) > 0)
            i++;

        if (dataEntries.getByIndex(i).dpi.equals(dpi))
            return this;
        if (isLeaf)
            return null;
        return children.getByIndex(i).findClienteByDpi(dpi);
    }
}
