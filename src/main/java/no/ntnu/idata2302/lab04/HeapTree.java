package no.ntnu.idata2302.lab04;

public class HeapTree {
    private int[] heap;
    private int size;
    private int maxSize;

    // Initializing front as static with unity
    private static final int FRONT = 1;


    /**
     * The constructor for the tree.
     *
     * @param maxSize is the maximum number of nodes in the tree
     */
    public HeapTree(int maxSize)
    {

        // This keyword refers to current object itself
        this.maxSize = maxSize;
        this.size = 0;

        heap = new int[this.maxSize + 1];
        heap[0] = Integer.MIN_VALUE;
    }

    /**
     * Get the position of the parent of the given int.
     *
     * @param position the position of the node
     * @return the position of the parent
     */
    private int getParent(int position) { return position / 2; }


    /**
     * Get the position of the left child of a specific node.
     *
     * @param position of the node
     * @return the position of the left child
     */
    private int getLeftChild(int position) { return (2 * position); }


    /**
     * Get the right child of the node.
     *
     * @param position of the node
     * @return the position of the right child
     */
    private int getRightChild(int position)
    {
        return (2 * position) + 1;
    }

    /**
     * Check if a given node is a leaf or not.
     *
     * @param position the position of the node
     * @return true if the node is a leaf, and false if it is a branch
     */
    private boolean isLeaf(int position)
    {

        if (position > (size / 2)) {
            return true;
        }

        return false;
    }

    /**
     * Swap the position of two nodes.
     *
     * @param firstPos the position of the first node
     * @param secondPos the position of the second node
     */
    private void swap(int firstPos, int secondPos)
    {

        int tmp;
        tmp = heap[firstPos];

        heap[firstPos] = heap[secondPos];
        heap[secondPos] = tmp;
    }


    /**
     * Heap the nodes in place, at the right position.
     *
     * @param position of the start
     */
    private void minHeap(int position)
    {
        if(!isLeaf(position)){
            int swapPos = position;
            // Swap with the minimum of the two children
            // to check if right child exists, otherwise default value will be '0'
            // and that will be swapped with parent node.
            if(getRightChild(position) <= size)
                swapPos = heap[getLeftChild(position)]< heap[getRightChild(position)]? getLeftChild(position): getRightChild(position);
            else
                swapPos= heap[getLeftChild(position)];

            if(heap[position]> heap[getLeftChild(position)] || heap[position]> heap[getRightChild(position)]){
                swap(position,swapPos);
                minHeap(swapPos);
            }

        }
    }


    /**
     * Insert a node(k) into the heap.
     *
     * @param k element that shall be inserted to the heap
     */
    public void insert(int k)
    {

        if (size >= maxSize) {
            return;
        }

        heap[++size] = k;
        int current = size;

        while (heap[current] < heap[getParent(current)]) {
            swap(current, getParent(current));
            current = getParent(current);
        }
    }


    /**
     * Print the tree. Print the parent node with the corresponding children.
     */
    public void print()
    {
        for (int i = 1; i <= size / 2; i++) {

            // Printing the parent and both children
            System.out.print(
                    " PARENT : " + heap[i]
                            + " LEFT CHILD : " + heap[2 * i]
                            + " RIGHT CHILD :" + heap[2 * i + 1]);

            // By here new line is required
            System.out.println();
        }
    }


    /**
     * Removes min node in the heap. And return the minimum value.
     *
     * @return the minimum of the heap aka the root node
     */
    public int extractMin()
    {

        int popped = heap[FRONT];
        heap[FRONT] = heap[size--];
        minHeap(FRONT);

        return popped;
    }

    /**
     * Starts the heap, and fill it with some values.
     */
    public static void main(String[] arg)
    {

        System.out.println("The Min Heap is ");

        HeapTree minHeap = new HeapTree(15);


        // Custom input entries
        minHeap.insert(3);
        minHeap.insert(10);
        minHeap.insert(2);
        minHeap.insert(18);
        minHeap.insert(9);
        minHeap.insert(96);
        minHeap.insert(17);
        minHeap.insert(7);
        minHeap.insert(25);

        minHeap.print();

        // Removing minimum value from above heap
        // and printing it
        System.out.println("The Min val is "
                + minHeap.extractMin());

}
}
