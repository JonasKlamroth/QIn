class MaxCut{

    public static boolean[] solve() {

        //optimal parameters for qaoa
        float ss = -11.2055919296600f; //Rx
        float y = 2.05324703239788f; //Rz
        float cos_ss2 = 0.77732784406f;
        float sin_ss2 = 0.62909571834f;
        float cos_y2 = 0.51771056098f;
        float sin_y2 = 0.85555582812f;

        final float[][] rx = new float[][]{
                new float[]{cos_ss2 , 0.0f},
                new float[]{0.0f , cos_ss2}
        };
        final float[][] rx_i = new float[][]{
                new float[]{0.0f , -sin_ss2},
                new float[]{-sin_ss2 , 0.0f}
        };

        final float[][] rz = new float[][]{
                new float[]{cos_y2 , 0.0f},
                new float[]{0.0f , cos_y2}
        };
        final float[][] rz_i = new float[][]{
                new float[]{-sin_y2 , 0.0f},
                new float[]{0.0f , sin_y2}
        };

        CircuitMock c = new CircuitMock( 4);



        c.h(0);
        c.h(1);
        c.h(2);
        c.h(3);

        c.swap(0, 1);
        c.cx(0, 1);
        c.u(rz, rz_i, 1);
        c.cx(0, 1);
        c.swap(0, 1);

        c.swap(1, 2);
        c.swap(0, 1);
        c.cx(0, 1);
        c.u(rz, rz_i, 1);
        c.cx(0, 1);
        c.swap(0, 1);
        c.swap(1, 2);

        c.swap(1,2);
        c.cx(1, 2);
        c.u(rz, rz_i, 2);
        c.cx(1, 2);

        c.swap(2,3);
        c.swap(1,2);
        c.swap(0,1);
        c.cx(0, 1);
        c.u(rz, rz_i, 1);
        c.cx(0, 1);
        c.swap(0,1);
        c.swap(1,2);
        c.swap(2,3);


        c.u(rx, rx_i, 0);
        c.u(rx, rx_i, 1);

        c.swap(2,3);
        c.cx(2, 3);
        c.u(rz, rz_i, 3);
        c.cx(2, 3);
        c.swap(2,3);

        c.u(rx, rx_i, 2);
        c.u(rx, rx_i, 3);

        boolean res[] = {c.measure(0), c.measure(1), c.measure(2),c.measure(3)};
        return res;
    }

}