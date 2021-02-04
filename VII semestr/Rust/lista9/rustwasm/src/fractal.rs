use std::ops::{Add, Sub, Mul};

#[derive(Copy, Clone)]
pub struct Complex {
    real     : f32,
    imaginary: f32,
    
}

impl Complex {
    pub fn new(real:f32,imaginary:f32)->Complex {
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
pub fn m_iter(c:Complex, thresh:f32, max_steps:i32) -> i32 {
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


//fn main() {
    
    //let size:usize = 1024;
    //image = draw(4.0, 50, image);
    //let image_string = image.to_string();

    //let mut image_file = File::create("colorimage.ppm").expect("create file error");
    //image_file.write_all(image_string.as_bytes()).expect("write file error");

//}
