export interface IComputerSpec {
  numberOfCores: number;
  singleCoreClock: number;
  ramSize: number;
  vRamSize: number;
  gpuHashRate: number;
}

export interface IPersonalComponent {
  cpuName: string;
  gpuName: string;
  ramName: string;
  caseName: string;
  storageName: string;
  motherboardName: string;
  powerSupplyName: string;
}

export const emptyPersonalComponent = {
  cpuName: '',
  gpuName: '',
  ramName: '',
  caseName: '',
  storageName: '',
  motherboardName: '',
  powerSupplyName: '',
};
