
export interface IMalfunction{
  processor: string;
  memoryType : string;
  memorySize : string;
  storageType : string;
  graphicCard : string;
  motherboard : string;
  mouseManufacturer : string;
  keyboardType : string;
  keyboardCaseType : string;
  mouseType : string;
  operatingSystem : string;
  antivirusSoftware : string;
  monitor : string;
  cmosUsage : string;
  connectionType: string;
  symptoms: string[];
}

export const emptyMalfunction ={
  processor: '',
  memoryType : '',
  memorySize : '',
  storageType : '',
  graphicCard : '',
  motherboard : '',
  mouseManufacturer : '',
  keyboardType : '',
  keyboardCaseType : '',
  mouseType : '',
  operatingSystem : '',
  antivirusSoftware : '',
  monitor : '',
  cmosUsage : '',
  connectionType: '',
  symptoms: []
}
