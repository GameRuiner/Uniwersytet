use std::fs::File;
use std::io::Write;
use std::ops::{Add, Sub, Mul};

#[derive(Copy, Clone)]
struct Color {
    color: u32,
}

impl Color {
    fn new(red:u8, green:u8, blue:u8) -> Color{
        Self {
            color : (red as u32)<<16 | (green as u32) << 8 | blue as u32,
        } 
    }

    fn red(&self) -> u8 {
        (self.color>>16) as u8
    }

    fn green(&self) -> u8 {
        (self.color>>8) as u8
    }

    fn blue(&self) -> u8 {
        (self.color>>0) as u8
    }
}


struct Image {
    height: usize,
    width : usize,
    data  : Vec<u32>, 
}

impl Image {
    
    fn new(height:usize, width:usize)->Image {
        Self {
            height,
            width,
            data :vec![0;width*height],
        }
    }

    fn set_color(&mut self, x:usize, y:usize, red:u8, green:u8, blue:u8) {
        let color = Color::new(red, green, blue);
        self.data[self.width*y+x] = color.color; 

    }

    fn to_stringh(&self)->String {
        let mut res = "P3\n".to_string();
        res+="#Marko Golovko. Mandelbrot Set\n";
        res+=&format!("{} {}\n",self.width, self.height)[..];
        res+="255\n";

        for y in 0..self.height {
            for x in 0..self.width {
                let color = self.data[self.width*y+x];
                let red = (color>>16) as u8;
                let green = (color>>8) as u8;
                let blue = (color>>0) as u8;
                let color_string = format!("{} {} {}\n", red, green, blue);
                res+=&color_string;
            }
        }
        res
    }

}

#[derive(Copy, Clone)]
struct Complex {
    real     : f32,
    imaginary: f32,
    
}

impl Complex {
    fn new(real:f32,imaginary:f32)->Complex {
        Complex {
            real,
            imaginary,
        }
    }

}

impl Add for Complex {
    type Output = Complex;

    fn add (self, _rhs:Complex)->Complex {
        Complex {
            real: self.real + _rhs.real,
            imaginary: self.imaginary + _rhs.imaginary,
        }
    }
}

impl Sub for Complex {
    type Output = Complex;

    fn sub(self, _rhs:Complex)->Complex {
        Complex {
            real: self.real - _rhs.real,
            imaginary: self.imaginary - _rhs.imaginary,
        }
    }
}

impl Mul for Complex {
    type Output = Complex;

    fn mul(self, _rhs:Complex)->Complex {
        Complex {
            real: self.real*_rhs.real - self.imaginary*_rhs.imaginary,
            imaginary: self.imaginary*_rhs.real + self.real*_rhs.imaginary,
        }
    }
}

//Mandelbrot iter
fn m_iter(c:Complex, thresh:f32, max_steps:i32) -> i32 {
    // Z(0) = c
    let mut z_old=c;
    let mut i=1;
    while i < max_steps && (z_old*z_old).real<thresh {
        // Z(n) = (Z(n-1))^2 + c
        let z_new =z_old*z_old+c;
        z_old = z_new;
        i+=1;
    }
    i
}


fn draw(thresh:f32, max_steps:i32, mut img: Image, gradient: &Vec<Color>, color_n: usize) -> Image {
    
    //The left-most extent of the set ends with the spike at x = -2, and the right side extends out to approximately x = 0.47. 
    //The top and bottom are at approximately y = ± 1.12, respectively. 
    
    fn mapper(x:usize, y:usize, w:usize, h:usize) -> Complex{
        let mx = 2.47/(w-1) as f32;
        let my = 2.26/(h-1) as f32;
        Complex::new(mx*x as f32 - 2.0, my*y as f32 - 1.13)
    }

    fn _mapper2(x:usize, y:usize, w:usize, h:usize) -> Complex{
        let mx = 1.23/(w-1) as f32;
        let my = 1.13/(h-1) as f32;
        Complex::new(mx*x as f32 - 1.0, my*y as f32)
    }

    fn painter(n:usize, colors: &Vec<Color>, state:u8) -> Color {
        let floor = (n as f32 / 255.0*state as f32).floor() as usize;
        let ceil = (n as f32 / 255.0*state as f32).ceil() as usize;
        let level = 255.0/(n+1) as f32;
        let current_state = state as f32 - level*floor as f32;
        let percentage = level/current_state;
        let floor_color = colors[floor];
        let ceil_color = colors[ceil] ;
        let red = (floor_color.red() as f32 + (ceil_color.red() as i32 - floor_color.red() as i32) as f32 * percentage) as u8;
        let green = (floor_color.green() as f32 + (ceil_color.green() as i32 - floor_color.green() as i32) as f32 * percentage) as u8;
        let blue = (floor_color.blue() as f32 + (ceil_color.blue() as i32 - floor_color.blue() as i32) as f32 * percentage) as u8;
        Color::new(red, green, blue)
    }

    let mult = 255/max_steps;
    for x in 0..img.width{
        for y in 0..img.height{
            let it = m_iter(mapper(x,y,img.width, img.height), thresh, max_steps);
            //img.set_color(x as usize, y as usize, 255-(it*mult) as u8, 255-(it*mult) as u8, 255-(it*mult) as u8);
            let color = painter(color_n-1, gradient, (it*mult) as u8);
            img.set_color(x, y, color.red(), color.green(), color.blue());
        }
    }
    img
}
fn main() {
    
    let size:usize = 1000;
    let mut image = Image::new(size,size);
    let start_color = Color::new(0,140,110);
    let middle_color = Color::new(0,0,0);
    let end_color = Color::new(0,240,250);
    let black_color = Color::new(0,0,0);
    let color_gradient = vec![middle_color, start_color, black_color, end_color];
    image = draw(4.0, 50, image, &color_gradient, color_gradient.len());
    let image_string = image.to_stringh();

    let mut image_file = File::create("colorimage.ppm").expect("create file error");
    image_file.write_all(image_string.as_bytes()).expect("write file error");

}
