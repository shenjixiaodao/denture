import request from '@/utils/request'

export function queryByDentureId(dentureId) {
  return request({
    url: '/manufacture/queryByDentureId',
    method: 'get',
    params: { dentureId: dentureId }
  })
}
