export interface Partner {

  alias: string;
  type: string;
  direction: PartnerDirection;
  application?: string;
  processedFlowType: PartnerFlowType;
  description: string;
}

export type PartnerDirection = 'INBOUND' | 'OUTBOUND';

export type PartnerFlowType = 'MESSAGE' | 'ALERTING' | 'NOTIFICATION';
