extern crate image;
use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;

//use image::{GenericImage, GenericImageView, ImageBuffer, RgbImage};

struct Position {
    x :  f64,
    y :  f64,
    dir : f64,
}


fn main() {
    
    let imgx = 1000;
    let imgy = 1000;
    
    let mut imgbuf = image::ImageBuffer::new(imgx, imgy);
    
    // Create a new ImgBuf with width: imgx and height: imgy
    // Iterate over the coordinates and pixels of the image
    for (_x, _y, pixel) in imgbuf.enumerate_pixels_mut() {
        *pixel = image::Rgb([255 as u8, 255, 255]);
    }

    let mut position = Position {x : 500.0, y : 500.0, dir : 0.0};
    let mut color: image::Rgb<u8> = image::Rgb([0 as u8, 0, 0]);

    // read logo programm
    let mut input = vec![];
    if let Ok(lines) = read_lines("./input.txt") {
        // Consumes the iterator, returns an (Optional) String
        for line in lines {
            if let Ok(ip) = line {
                //input.push( ip.parse::<i32>().unwrap());
                input.push(ip);
            }
        }
    }


    // evaluate
    for line in input {
        let line_split = line.split_ascii_whitespace().collect::<Vec<_>>();
        let command = line_split[0];

        match command {
            "fd"|"forward" => { 
                let steps = line_split[1].parse::<u32>().unwrap();
                forward(steps, &mut imgbuf, &mut position, color);

            },
            "bk"|"back" => { 
                let steps = line_split[1].parse::<u32>().unwrap();
                back(steps, &mut imgbuf, &mut position, color);

            },
            "lt"|"left" => { 
                let degrees = line_split[1].parse::<u32>().unwrap();
                position.dir = (position.dir + (degrees % 360) as f64) % 360.0;

            },
            "rt"|"right" => { 
                let degrees = line_split[1].parse::<u32>().unwrap();
                position.dir = (position.dir + 360.0 - (degrees % 360) as f64) % 360.0;

            },
            "setpencolor" => {
                let r = line_split[1].get(1..).unwrap().parse::<u8>().unwrap();
                let g = line_split[2].parse::<u8>().unwrap();
                let b = line_split[3].get(0..line_split[3].len()-1).unwrap().parse::<u8>().unwrap();
                color = image::Rgb([r, g, b]);
            },
            "clearscreen" => {
                for (_x, _y, pixel) in imgbuf.enumerate_pixels_mut() {
                    *pixel = image::Rgb([255 as u8, 255, 255]);
                }
                position = Position {x : 500.0, y : 500.0, dir : 0.0};
            }
            _    => ()
        }
    }
    
    // Save the image as “fractal.png”, the format is deduced from the path
    imgbuf.save("star.png").unwrap();
}

fn back(steps: u32, imgbuf: &mut image::ImageBuffer<image::Rgb<u8>, Vec<u8>>, position: &mut Position, color: image::Rgb<u8>  ) {
    for _i in 0..steps {
        position.y += (position.dir * std::f64::consts::PI / 180.0).cos();
        position.x += (position.dir * std::f64::consts::PI / 180.0).sin();
        let pixel = imgbuf.get_pixel_mut(position.x as u32, position.y as u32);
        //let image::Rgb(data) = *pixel;
        *pixel = color;
    }

}

fn forward(steps: u32, imgbuf: &mut image::ImageBuffer<image::Rgb<u8>, Vec<u8>>, position: &mut Position, color: image::Rgb<u8> ) {
    for _i in 0..steps {
        position.y -= (position.dir * std::f64::consts::PI / 180.0).cos();
        position.x -= (position.dir * std::f64::consts::PI / 180.0).sin();

        let pixel = imgbuf.get_pixel_mut(position.x as u32, position.y as u32);
        //let image::Rgb(data) = *pixel;
        *pixel = color;
    }

}

fn read_lines<P>(filename: P) -> io::Result<io::Lines<io::BufReader<File>>>
where P: AsRef<Path>, {
    let file = File::open(filename)?;
    Ok(io::BufReader::new(file).lines())
}
