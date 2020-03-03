package cn.yangwanhao.bookstore.common.util;

import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 生成验证码
 * @author 杨万浩
 */
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CaptchaUtil {

    public static CaptchaImg getCaptcha() {
       return new CaptchaImg(GlobalConstant.Captcha.WIDTH, GlobalConstant.Captcha.HEIGHT, GlobalConstant.Captcha.CAPTCHA_SIZE, GlobalConstant.Captcha.LINE_COUNT);
    }

    public static class CaptchaImg {
        // 图片的宽度
        private int width;
        // 图片的高度
        private int height;
        // 验证码字符个数
        private int codeCount;
        // 验证码干扰线数
        private int lineCount;
        // 验证码
        @Getter
        private String code = null;
        // 验证码图片Buffer
        private BufferedImage buffImg = null;
        Random random = new Random();

        /**
         * 自定义宽、高、字符个数和干扰线条数
         * @param width 宽
         * @param height 高
         * @param codeCount 验证码位数
         * @param lineCount 干扰线数
         */
        private CaptchaImg(int width, int height, int codeCount, int lineCount) {
            this.width = width;
            this.height = height;
            this.codeCount = codeCount;
            this.lineCount = lineCount;
            creatImage();
        }

        /**
         * 输出图片
         * @param sos 输出流对象
         */
        public void write(OutputStream sos) throws IOException {
            ImageIO.write(buffImg, "png", sos);
            sos.close();
        }

        /**
         * 生成图片
         */
        private void creatImage() {
            // 字体的宽度
            int fontWidth = width / codeCount;
            // 字体的高度
            int fontHeight = height - 5;
            int codeYline = height - 8;
            // 图像buffer
            buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = buffImg.getGraphics();
            // 设置背景色
            g.setColor(getRandColor(200, 250));
            g.fillRect(0, 0, width, height);
            // 设置字体
            Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
            g.setFont(font);
            // 设置干扰线
            for (int i = 0; i < lineCount; i++) {
                int xs = random.nextInt(width);
                int ys = random.nextInt(height);
                int xe = xs + random.nextInt(width);
                int ye = ys + random.nextInt(height);
                g.setColor(getRandColor(1, 255));
                g.drawLine(xs, ys, xe, ye);
            }
            // 添加噪点
            float yawpRate = 0.01f;// 噪声率
            int area = (int) (yawpRate * width * height);
            for (int i = 0; i < area; i++) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                buffImg.setRGB(x, y, random.nextInt(255));
            }
            // 得到随机字符
            String str1 = randomStr(GlobalConstant.Captcha.CAPTCHA_SIZE, GlobalConstant.Captcha.CAPTCHA_SOURCE);
            this.code = str1;
            for (int i = 0; i < codeCount; i++) {
                String strRand = str1.substring(i, i + 1);
                g.setColor(getRandColor(1, 255));
                // a为要画出来的东西，x和y表示要画的东西最左侧字符的基线位于此图形上下文坐标系的 (x, y) 位置处
                g.drawString(strRand, i * fontWidth + 3, codeYline);
            }
        }

        /**
         * 得到随机字符串
         * @return 随机字符串
         */
        private String randomStr(int size, char [] source) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                int index = random.nextInt(source.length);
                sb.append(source[index]);
            }
            return sb.toString();
        }

        /**
         * 在给定范围中得到随机颜色
         * @param fc fc
         * @param bc bc
         * @return color
         */
        private Color getRandColor(int fc, int bc) {
            int r = fc + random.nextInt(bc - fc);
            int g = fc + random.nextInt(bc - fc);
            int b = fc + random.nextInt(bc - fc);
            return new Color(r, g, b);
        }
    }
}