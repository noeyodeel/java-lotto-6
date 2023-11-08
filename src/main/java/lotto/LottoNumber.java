package lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import camp.nextstep.edu.missionutils.Console;

public class LottoNumber {
  public List<Integer> lottoNumber() {
    while (true) {
      try {
        System.out.println("당첨 번호를 입력해 주세요.");
        List<Integer> winningNumbers = readNumbers(Console.readLine());
        validateNumbers(winningNumbers);
        Collections.sort(winningNumbers);
        validateNoDuplicates(winningNumbers);

        return winningNumbers;
      } catch (NumberFormatException e) {
        System.out.println("[ERROR] 숫자가 아닌 입력입니다.");
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public int bonusNumber(List<Integer> winningNumbers) {
    while (true) {
      try {
        System.out.println("보너스 번호를 입력해 주세요.");
        int bonusNumber = Integer.parseInt(Console.readLine());
        validateBonusNumber(bonusNumber, winningNumbers);
        return bonusNumber;
      } catch (NumberFormatException e) {
        System.out.println("[ERROR] 숫자가 아닌 입력입니다.");
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private void validateBonusNumber(int number, List<Integer> winningNumbers) {
    if (number < 1 || number > 45) {
      throw new IllegalArgumentException("[ERROR] 1에서 45 사이의 숫자를 입력해주세요.");
    }

    if (winningNumbers.contains(number)) {
      throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨번호와 중복될 수 없습니다.");
    }
  }

  // 쉼표로 구분된 숫자 문자열 -> 정수 리스트
  private static List<Integer> readNumbers(String readLine) {

    return List.of(readLine.split(",")).stream().map(Integer::parseInt)
        .collect(Collectors.toList());
  }


  private void validateNumbers(List<Integer> numbers) {
    if (numbers.size() != 6) {
      throw new IllegalArgumentException("[ERROR] 6개의 숫자를 입력해주세요.");
    }

    for (int number : numbers) {
      if (number < 1 || number > 45) {
        throw new IllegalArgumentException("[ERROR] 1에서 45 사이의 숫자를 입력해주세요.");
      }
    }
  }


  private void validateNoDuplicates(List<Integer> numbers) {
    for (int i = 0; i < numbers.size(); i++) {
      for (int j = i + 1; j < numbers.size(); j++) {
        if (numbers.get(i).equals(numbers.get(j))) {
          throw new IllegalArgumentException("[ERROR] 숫자가 중복되었습니다.");
        }
      }
    }
  }

}

