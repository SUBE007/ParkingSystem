package parkingstrategy;

public enum AssignLot {
    LOT{
        //@Override
        public String assignLOT(int slotNo) {
            if (slotNo >= 0 && slotNo <= 20){
                return "Park1";
            }
            if (slotNo >= 21 && slotNo <= 40){
                return "Park2";
            }
            if (slotNo >= 41 && slotNo <= 60){
                return "Park3";
            }
            if (slotNo > 61 && slotNo <= 80){
                return "Park4";
            }
            if (slotNo > 61 && slotNo <= 80){
                return "Park5";
            }
             return null;
        }
    }

}
