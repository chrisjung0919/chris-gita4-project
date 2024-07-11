    class Calculator {
        private double rentalRate;
        private double mileageRate;
        private int numDays;
        private double beginOdometer;
        private double endOdometer;
        private static double totalAmountReceived = 0.0;
        private static int carsReturned = 0;

        public double calculateTotalCharge() {
            double milesDriven = endOdometer - beginOdometer;
            return rentalRate * numDays + mileageRate * milesDriven;
        }

        public void updateSummary(double totalCharge) {
            totalAmountReceived += totalCharge;
            carsReturned++;
        }

        public double getMilesDriven() {
            return endOdometer - beginOdometer;
        }

        // Getters for summary properties
        public double getTotalAmountReceived() {
            return totalAmountReceived;
        }

        public int getCarsReturned() {
            return carsReturned;
        }

        public double getAverageAmountSpent() {
            return totalAmountReceived / carsReturned;
        }

        // Add setters for calculator properties
        public void setRentalRate(double rentalRate) {
            this.rentalRate = rentalRate;
        }

        public void setMileageRate(double mileageRate) {
            this.mileageRate = mileageRate;
        }

        public void setNumDays(int numDays) {
            this.numDays = numDays;
        }

        public void setBeginOdometer(double beginOdometer) {
            this.beginOdometer = beginOdometer;
        }

        public void setEndOdometer(double endOdometer) {
            this.endOdometer = endOdometer;
        }
    }
