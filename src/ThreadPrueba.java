public class ThreadPrueba {

        private static class Score {
            public int maxScore;
        }

        public static void main(String args[]) throws Exception {
            Score score = new Score();
            score.maxScore = 1;

            System.out.println("Initial maxScore: " + score.maxScore);

            Thread student = new Thread() {

                @Override
                public void run() {
                    score.maxScore++;
                }
            };

            student.start();
            student.join(1000000); // waiting for thread to finish

            System.out.println("Result maxScore: " + score.maxScore);
        }
}
