class MaxCut{

    public static boolean[] solve() {

        //optimal parameters for qaoa
        float ss = 13.9720198865283f; //Rx
        float y = 2.07703556549873f; //Rz
        float cos_ss2 = 0.763019458f;
        float sin_ss2 = 0.64637551524f;
        float cos_y2 = 0.50749797111f;
        float sin_y2 = 0.86165295178f;

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

        c.cx(1, 0);
        c.u(rz, rz_i, 0);
        c.cx(1, 0);

        c.cx(2, 0);
        c.u(rz, rz_i, 0);
        c.cx(2, 0);

        c.cx(2, 1);
        c.cx(3, 0);
        c.u(rz, rz_i, 0);
        c.u(rz, rz_i, 1);
        c.cx(2, 1);
        c.cx(3, 0);

        c.u(rx, rx_i, 0);
        c.u(rx, rx_i, 1);

        c.cx(3, 2);

        c.u(rz, rz_i, 2);

        c.cx(3, 2);

        c.u(rx, rx_i, 2);
        c.u(rx, rx_i, 3);

        boolean res[] = {c.measure(0), c.measure(1), c.measure(2),c.measure(3)};
        return res;
    }

}