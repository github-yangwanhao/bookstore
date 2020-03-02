package cn.yangwanhao.bookstore.common.util;

import cn.yangwanhao.bookstore.common.properties.PictureCaptchaProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/10/26 14:06
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CaptchaUtils {

    public static CaptchaImg getCaptchaImg() {
        return new CaptchaImg();
    }

    public static class CaptchaImg {
        // 验证码长度
        private Integer captchaSize;
        // 验证码源
        private char[] source;
        // 图片的宽度
        private int imgWidth;
        // 图片的高度
        private int imgHeight;
        // 验证码干扰线数
        private int lineCount;
        // 验证码
        @Getter
        private String code = null;
        // 验证码图片Buffer
        private BufferedImage buffImg = null;
        Random random = new Random();

        private CaptchaImg() {
            this.captchaSize = PictureCaptchaProperties.CAPTCHA_SIZE;
            this.source = PictureCaptchaProperties.GENERATE_CAPTCHA_SOURCE;
            this.imgWidth = PictureCaptchaProperties.IMG_WIDTH;
            this.imgHeight = PictureCaptchaProperties.IMG_HEIGHT;
            this.lineCount = PictureCaptchaProperties.IMG_LINE_COUNT;
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
            int fontWidth = imgWidth / captchaSize;
            // 字体的高度
            int fontHeight = imgHeight - 5;
            int codeYline = imgHeight - 8;
            // 图像buffer
            buffImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics g = buffImg.getGraphics();
            // 设置背景色
            g.setColor(getRandColor(200, 250));
            g.fillRect(0, 0, imgWidth, imgHeight);
            // 设置字体
            Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
            g.setFont(font);
            // 设置干扰线
            for (int i = 0; i < lineCount; i++) {
                int xs = random.nextInt(imgWidth);
                int ys = random.nextInt(imgHeight);
                int xe = xs + random.nextInt(imgWidth);
                int ye = ys + random.nextInt(imgHeight);
                g.setColor(getRandColor(1, 255));
                g.drawLine(xs, ys, xe, ye);
            }
            // 添加噪点
            float yawpRate = 0.01f;// 噪声率
            int area = (int) (yawpRate * imgWidth * imgHeight);
            for (int i = 0; i < area; i++) {
                int x = random.nextInt(imgWidth);
                int y = random.nextInt(imgHeight);
                buffImg.setRGB(x, y, random.nextInt(255));
            }
            // 得到随机字符
            String str1 = randomStr(captchaSize, source);
            this.code = str1;
            for (int i = 0; i < captchaSize; i++) {
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
