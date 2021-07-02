
public class Deutsch {
    public boolean applyDeutsch(boolean[] f) {
        float[][] m = new float[][]{
                new float[]{!f[0] ? 1.0f : 0.0f, f[0] ? 1.0f :0.0f, 0.f, 0.f},
                new float[]{f[0] ? 1.0f : 0.0f, !f[0] ? 1.0f :0.0f, 0.f, 0.f},
                new float[]{0.f, 0.f, !f[1] ? 1.0f : 0.0f, f[1] ? 1.0f :0.0f},
                new float[]{0.f, 0.f, f[1] ? 1.0f : 0.0f, !f[1] ? 1.0f :0.0f}
        };
        CircuitMock c = new CircuitMock( 2);
        c.x(1);
        c.h(1);
        c.h(0);
        c.u(m, 0, 1);
        c.h(0);
        return c.measureMax(0);
    }
}
