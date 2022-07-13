export interface IMalfunction {
  processor: string;
  memory: string;
  memoryType: string;
  memorySize: string;
  storageType: string;
  graphicCard: string;
  motherboard: string;
  mouseManufacturer: string;
  keyboardType: string;
  keyboardCaseType: string;
  mouseType: string;
  operatingSystem: string;
  antivirusSoftware: string;
  monitor: string;
  case: string;
  storage: string;
  cmosUsage: string;
  connectionType: string;
  powerSupply: string;
  symptoms: string[];
}

export const emptyMalfunction = {
  processor: '',
  memory: '',
  memoryType: '',
  memorySize: '',
  storageType: '',
  graphicCard: '',
  motherboard: '',
  mouseManufacturer: '',
  keyboardType: '',
  keyboardCaseType: '',
  mouseType: '',
  operatingSystem: '',
  antivirusSoftware: '',
  monitor: '',
  case: '',
  storage: '',
  cmosUsage: '',
  connectionType: '',
  powerSupply: '',
  symptoms: [],
};
