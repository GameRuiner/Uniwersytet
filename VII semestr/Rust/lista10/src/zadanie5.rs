#[allow(dead_code)]
fn chessboard_cell_color(cell1: &str, cell2: &str) -> bool {
    (get_num(cell1) - get_num(cell2)) % 2 == 0
}

fn get_num(cell: &str) -> i32 {
    //let vec_char = cell.chars().collect::<Vec<_>>();
    //cell.chars().nth(0).unwrap();
    let ch_num = cell.chars().nth(0).unwrap() as i32 - 'A' as i32 + 1;
    let num_num = cell.chars().nth(1).unwrap().to_string().parse::<i32>().unwrap();
    ch_num - num_num
}


#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn basic_tests() {
        assert_eq!(chessboard_cell_color("A1", "C3"), true);
        assert_eq!(chessboard_cell_color("A1", "H3"), false);
        assert_eq!(chessboard_cell_color("A1", "A2"), false);
        assert_eq!(chessboard_cell_color("A1", "C1"), true);
        assert_eq!(chessboard_cell_color("A1", "A1"), true);
    }
}