use std::collections::HashSet;

#[allow(dead_code)]
struct Sudoku{
    data: Vec<Vec<u32>>,
}

#[allow(dead_code)]
impl Sudoku{
    fn is_valid(&self) -> bool {
      let size = self.data.len();

      let sqrtsize = (size as f64).sqrt(); 
      
      if sqrtsize.powi(2) != size as f64 {
          return false
      }

      let mut  sum = 0;

      for line in &self.data {
        let mut local_sum = 0;
        if line.len() == 0 {
            return  false;
        }  
        for element in line {
            if *element > size as u32 || *element < 1 {
                return false;
            }
            local_sum += element;
          }
        if sum == 0 {
            sum = local_sum;
        } else {
            if sum != local_sum {
                return false;
            }
        }
      }

      for i in 0..size {
          let mut local_sum = 0;

          for j in 0..size {
              local_sum += self.data[j][i];
          }

          if sum != local_sum {
            return false;
          }
      }

      
      let sqrtsize = sqrtsize as usize;
      
      
        
        for k in 0..sqrtsize {
            let mut nums = HashSet::<u32>::new();
        for i in 0..sqrtsize {
        for j in 0..sqrtsize {
            let num = self.data[i][j+(k*sqrtsize)];
            println!("{} {} {} {}", k, i, j, num);
            if nums.contains(&num) {
                return  false;
            } else {
                nums.insert(num);
            }
        }
    }

      }

      true
    }
}

#[test]
fn good_sudoku1() {
    let good_sudoku_1 = Sudoku{
        data: vec![
                vec![7,8,4, 1,5,9, 3,2,6],
                vec![5,3,9, 6,7,2, 8,4,1],
                vec![6,1,2, 4,3,8, 7,5,9],

                vec![9,2,8, 7,1,5, 4,6,3],
                vec![3,5,7, 8,4,6, 1,9,2],
                vec![4,6,1, 9,2,3, 5,8,7],
                
                vec![8,7,6, 3,9,4, 2,1,5],
                vec![2,4,3, 5,6,1, 9,7,8],
                vec![1,9,5, 2,8,7, 6,3,4]
            ]
    };
    assert!(good_sudoku_1.is_valid());
}

#[test]
fn good_sudoku2() {
    let good_sudoku_2 = Sudoku{
        data: vec![
                vec![1, 4,  2, 3],
                vec![3, 2,  4, 1],
        
                vec![4, 1,  3, 2],
                vec![2, 3,  1, 4],
            ]
    };
    assert!(good_sudoku_2.is_valid());
}

#[test]
fn good_sudoku3() {
    let good_sudoku_1 = Sudoku{
        data: vec![
               vec![1, 6, 5,  9, 3, 8,  2, 4, 7], 
               vec![7, 8, 9,  2, 5, 4,  6, 3, 1], 
               vec![4, 2, 3,  6, 7, 1,  8, 5, 9], 

               vec![2, 7, 6,  1, 8, 3,  4, 9, 5], 
               vec![3, 9, 4,  5, 6, 2,  7, 1, 8], 
               vec![8, 5, 1,  7, 4, 9,  3, 2, 6], 

               vec![5, 4, 2,  8, 9, 6,  1, 7, 3], 
               vec![6, 1, 7,  3, 2, 5,  9, 8, 4], 
               vec![9, 3, 8,  4, 1, 7,  5, 6, 2]
               ]
    };
    assert!(good_sudoku_1.is_valid());
}



#[test]
fn bad_sudoku1() {
    let bad_sudoku_1 = Sudoku{
        data: vec![
                vec![1,2,3, 4,5,6, 7,8,9],
                vec![1,2,3, 4,5,6, 7,8,9],
                vec![1,2,3, 4,5,6, 7,8,9],

                vec![1,2,3, 4,5,6, 7,8,9],
                vec![1,2,3, 4,5,6, 7,8,9],
                vec![1,2,3, 4,5,6, 7,8,9],
                
                vec![1,2,3, 4,5,6, 7,8,9],
                vec![1,2,3, 4,5,6, 7,8,9],
                vec![1,2,3, 4,5,6, 7,8,9],
            ]
    };
    assert!(!bad_sudoku_1.is_valid());
}

#[test]
fn bad_sudoku2 () {    
    let bad_sudoku_2 = Sudoku{
        data: vec![
                vec![1,2,3,4,5],
                vec![1,2,3,4],
                vec![1,2,3,4],
                vec![1],
            ]
    };
    assert!(!bad_sudoku_2.is_valid());
}
