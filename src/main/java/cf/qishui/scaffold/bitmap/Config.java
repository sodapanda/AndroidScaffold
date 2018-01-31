package cf.qishui.scaffold.bitmap;

import com.google.common.base.Objects;

public class Config {
    public int height;
    public int width;
    public int color;
    public float radius;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Config config = (Config) o;
        return height == config.height &&
                width == config.width &&
                color == config.color &&
                radius == config.radius;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(height, width, color, radius);
    }
}