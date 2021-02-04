pub fn execute(code: &str) -> String {
    // Implement your RS1 interpreter here
    if code == "" {
      return "*".to_string();
    }

    let mut min_x = 0;
    let mut min_y = 0;
    let mut max_x = 0;
    let mut max_y = 0;
    let mut cur_x = 0;
    let mut cur_y = 0;
    let mut direction = 90;
    let mut accum = "".to_string();
    let mut last_comand = '0';

    for ch in code.chars() {
      if (ch == 'F' || ch == 'L' || ch == 'R') && accum != "".to_string() {
        let steps = accum.parse::<i32>().unwrap();
        for _i in 0..steps-1 {
          match last_comand {
            'F' => forwd(direction , &mut cur_x, &mut cur_y,  &mut max_x, &mut max_y, &mut min_x, &mut min_y),
            'L' => direction = (direction - 90 + 360) % 360,
            'R' => direction = (direction + 90) % 360,
            _   => (),
          }
        }
        accum = "".to_string();
      }
      match ch {
        'F' => forwd(direction ,  &mut cur_x, &mut cur_y,  &mut max_x, &mut max_y, &mut min_x, &mut min_y),
        'L' => direction = (direction - 90) % 360,
        'R' => direction = (direction + 90) % 360,
         _   =>  accum.push(ch),
      }

      if accum == "".to_string() {
        last_comand = ch;
      }

      if direction < 0 {
        direction = 360 + direction;
      }
    }

    if accum != "".to_string() {
      let steps = accum.parse::<i32>().unwrap();
      for _i in 0..steps-1 {
        match last_comand {
          'F' => forwd( direction , &mut cur_x, &mut cur_y,  &mut max_x, &mut max_y, &mut min_x, &mut min_y),
          'L' => direction = (direction - 90) % 360,
          'R' => direction = (direction + 90) % 360,
          _   => (),
        }
        if direction < 0 {
          direction = 360 + direction;
        }
      }
      
    }

    let mut vec_res = Vec::new();
    
    for i in 0..max_y-min_y+1 {
      let mut vec_line = vec![" "; (max_x-min_x+1) as usize];
      if i != max_y-min_y{
          vec_line.push("\r\n");
      }
      
      vec_res.push(vec_line);
    }


    let mut cur_x = 0;
    let mut cur_y = 0;
    let mut direction = 90;
    let mut accum = "".to_string();
    vec_res[-min_y as usize][-min_x as usize] = "*";

    

    for ch in code.chars() {

      if (ch == 'F' || ch == 'L' || ch == 'R') && accum != "".to_string() {
        let steps = accum.parse::<i32>().unwrap();
        for _i in 0..steps-1 {
          match last_comand {
            'F' => forwd( direction , &mut cur_x, &mut cur_y,  &mut max_x, &mut max_y, &mut min_x, &mut min_y),
            'L' => direction = (direction - 90) % 360,
            'R' => direction = (direction + 90) % 360,
             _  => (),
          }
          if direction < 0 {
            direction = 360 + direction;
          }
          vec_res[(cur_y - min_y) as usize][(cur_x - min_x) as usize] = "*";
        }
        accum = "".to_string();
      }

      match ch {
        'F' => forwd( direction ,  &mut cur_x, &mut cur_y,  &mut max_x, &mut max_y, &mut min_x, &mut min_y),
        'L' => direction = (direction - 90) % 360,
        'R' => direction = (direction + 90) % 360,
        _ =>  accum.push(ch),
      }

      if accum == "".to_string() {
        last_comand = ch;
      }

      vec_res[(cur_y - min_y) as usize][(cur_x - min_x) as usize] = "*";

      if direction < 0 {
        direction = 360 + direction;
      }
    }

    if accum != "".to_string() {
      let steps = accum.parse::<i32>().unwrap();
      for _i in 0..steps-1 {
        match last_comand {
          'F' => forwd( direction , &mut cur_x, &mut cur_y,  &mut max_x, &mut max_y, &mut min_x, &mut min_y),
          'L' => direction = (direction - 90) % 360,
          'R' => direction = (direction + 90) % 360,
          _   => (),
        }
        if direction < 0 {
          direction = 360 + direction;
        }
        vec_res[(cur_y - min_y) as usize][(cur_x - min_x) as usize] = "*";
      }
      
    }

    fn forwd(dir: i32, cur_x: &mut i32, cur_y: &mut i32, max_x: &mut i32, max_y: &mut i32, min_x: &mut i32, min_y: &mut i32) {
      match dir {
        90  => inc(cur_x, max_x, min_x, 1),
        180 => inc(cur_y, max_y, min_y, 1),
        270 => inc(cur_x, max_x, min_x, -1),
        0   => inc(cur_y, max_y, min_y, -1),
        _ => (),
    }
    }


    fn inc(cur: &mut i32, max: &mut i32, min: &mut i32, steps: i32) {
      *cur+= steps;
      if *cur > *max {
        *max = *cur;
      }
      if *cur < *min {
        *min = *cur;
      }
    }

    let mut vec_res2 = Vec::new();
    
    for vect in vec_res {
      vec_res2.push( vect.iter().map(|x| x.to_string()).collect::<String>());
    }

    let res_string = vec_res2.join("");
    
    res_string
  }




#[cfg(test)]
  macro_rules! expect_equal {
    ($actual:expr, $expected:expr $(,)*) => {{
      let actual = $actual;
      let expected = $expected;
      assert_eq!(actual, expected, "\ngot:\n{}\n\nexpected:\n{}\n", actual, expected);
    }};
  }

  
  #[cfg(test)]
  mod tests {
  use super::execute;
  #[test]
  fn test1() {
    expect_equal!(execute(""), "*");
  }


#[test]
fn test2() {
  expect_equal!(execute("FFFFF"), "******");
}


#[test]
fn test3() {
  expect_equal!(
    execute("FFFFFLFFFFFLFFFFFLFFFFFL"),
    "******\r\n*    *\r\n*    *\r\n*    *\r\n*    *\r\n******",
  );
}


#[test]
fn test4() {
  expect_equal!(
    execute("LFFFFFRFFFRFFFRFFFFFFF"),
    "    ****\r\n    *  *\r\n    *  *\r\n********\r\n    *   \r\n    *   ",
  );
}


#[test]
fn test5() {
  expect_equal!(
    execute("LF5RF3RF3RF7"),
    "    ****\r\n    *  *\r\n    *  *\r\n********\r\n    *   \r\n    *   ",
  );
}


#[test]
fn test6() {
  expect_equal!(
    execute("FFFLLFFFFFFRRFFFLFFFRRFFFFFFFF"),
    "   *   \r\n   *   \r\n   *   \r\n*******\r\n   *   \r\n   *   \r\n   *   \r\n   *   \r\n   *   ",
  );
}


#[test]
fn test7() {
  expect_equal!(
    execute("F3L2F6R2F3LF3R2F8"),
    "   *   \r\n   *   \r\n   *   \r\n*******\r\n   *   \r\n   *   \r\n   *   \r\n   *   \r\n   *   ",
  );
}
}